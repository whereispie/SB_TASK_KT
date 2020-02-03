import com.mongodb.Block
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.BasicCRUD
import tools.LibraryCurator
import tools.MongoConnect
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors


class LibraryImp : MongoConnect, BasicCRUD, LibraryCurator {
    private val configFile = "config.properties"

    override fun dataBaseConnect(dataBaseName: String, collectionName: String) {

        /** prepare data from configuration file to connect */
        val config = Properties()
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configFile
        config.load(FileInputStream(propertyFilePath))
        val mongoClusterAddress = config.getProperty("URL_PARAM")

        /** connect to MongoDB and get instance to work with */
        val uri = MongoClientURI(mongoClusterAddress)
        val mongoSession = MongoClient(uri)
        val database = mongoSession.getDatabase(dataBaseName)
        val collection: MongoCollection<Document> = database.getCollection(collectionName)

        /** 1. get collection from Mongo*/
        val findIterable = collection.find(Document())
        val printBlock = Block<Document> { document -> println(document.toJson()) }
        findIterable.forEach(printBlock)
        /** 2. convert to Java collection (HashMap?)*/


        /** 3. update database */
        val word: Document = Document("WORD", "Wolf")
            .append("WORD_COUNT", 1)

        insertOne(collection, word)

        /** close connection to DB */
        mongoSession.close()
    }


    override fun wordFilter(originalFile: String): List<String> {
        return Files.readAllLines(Paths.get("src/main/resources/test.txt"))
            .stream()
            .map { words: String -> words.split(" ".toRegex()).toTypedArray() }
            .flatMap { array: Array<String>? -> Arrays.stream(array) }
            .collect(Collectors.toList())
            .filter {
                it.matches("\\p{IsLatin}+".toRegex())
                        || it.matches("^\\p{IsCyrillic}+$".toRegex())
                        || it.matches("[+-]?([0-9]*[.])?[0-9]+".toRegex()) // []
                        || it.matches("[^\\d+\$]".toRegex())
            }
        // .filter { it.contains("привет") || it.contains("hi") } // take WORD from list

    }
}