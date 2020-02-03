import com.mongodb.Block
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.BasicCRUD
import tools.LibraryCurator
import tools.MongoConnect
import tools.BasicOperationSystemTools
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView
import kotlin.system.exitProcess


class LibraryImp : MongoConnect, BasicCRUD, LibraryCurator, BasicOperationSystemTools {
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


    override fun wordFilter(filePath: String): List<String> {
        return Files.readAllLines(Paths.get(filePath))
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
    }

    override fun chooseTextFile(): String {
        /** Choose file from GUI */
        val jfc = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
        val returnValue = jfc.showOpenDialog(null)
        var localOSFilePath = ""
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            val selectedFile = jfc.selectedFile
            localOSFilePath = selectedFile.absolutePath.toString()
            /** ELSE -> Console read() + check on common text types*/
        } else {
            println("[Please add filepath as absolute path, for example: /Users/see/resources/test.txt]")
            println("[Print EXIT to close application]")
            val readText = readLine()
            if (readText != null) {
                if (readText == "EXIT") exitProcess(0)
                if (readText.substringAfter(".").contains("txt") ||
                    readText.substringAfter(".").contains("doc") ||
                    readText.substringAfter(".").contains("odt") ||
                    readText.substringAfter(".").contains("pdf") ||
                    readText.substringAfter(".").contains("rtf") ||
                    readText.substringAfter(".").contains("tex")
                ) {
                    localOSFilePath = readText
                } else {
                    chooseTextFile()
                }
            }
        }
        return localOSFilePath
    }
}