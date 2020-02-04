import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoCursor
import org.bson.Document
import tools.BasicCRUD
import tools.BasicOperationSystemTools
import tools.MongoConnect
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileWriter
import java.io.PrintWriter
import java.util.*


class MongoConnect : MongoConnect, BasicCRUD, BasicOperationSystemTools {
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
        val system = SystemTools()
        system.saveToLocalFromMongo(collection)
        // todo > make separate function in BasicOperation* interface

        // todo > PARSE JSON fields (delete object id, and other rubbish)

        /** 2. checked local library with MongoDB (WORD + WORD_COUNT++) */

        // todo > READ mongoWordArchive into collection
        // todo > COMPARE mongoWordArchive & filteredWords into UploadList

        /** 3. update database */

        // todo ADD UploadList to MongoDB
        val word: Document = Document("WORD", "FROM_COMPARE_COLLECTION")
            .append("WORD_COUNT", 1)

        insertOne(collection, word)

        /** close connection to DB */
        mongoSession.close()
    }

}