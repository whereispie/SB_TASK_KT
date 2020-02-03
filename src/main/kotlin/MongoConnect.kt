import com.mongodb.Block
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.BasicCRUD
import tools.MongoConnect
import java.io.FileInputStream
import java.util.*


class MongoConnect : MongoConnect, BasicCRUD {
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

        /** 1. get collection from Mongo */
        val findIterable = collection.find(Document())
        val printBlock = Block<Document> { document -> println(document.toJson()) }
        findIterable.forEach(printBlock)

        /** 2. checked library after stream() (List?) */

        /** 3. checked local library with MongoDB (WORD + WORD_COUNT++) */

        /** 4. update database */
        val word: Document = Document("WORD", "Wolf")
            .append("WORD_COUNT", 1)

        insertOne(collection, word)

        /** close connection to DB */
        mongoSession.close()
    }


}