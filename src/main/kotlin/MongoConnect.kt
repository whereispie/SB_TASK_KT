import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates
import com.mongodb.client.model.Updates.combine
import com.mongodb.client.model.Updates.set
import org.bson.Document
import tools.BasicCRUD
import tools.BasicOperationSystemTools
import tools.MongoConnect
import java.io.FileInputStream
import java.util.*


val operationSystemTools = SystemTools()
const val configurationFile = "config.properties"

class MongoConnect : MongoConnect, BasicCRUD, BasicOperationSystemTools {

    override fun dataBaseConnect(dataBaseName: String, collectionName: String) {

        /** prepare data from configuration file to connect */
        val config = Properties()
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configurationFile
        config.load(FileInputStream(propertyFilePath))
        val clusterAddress = config.getProperty("URL_PARAM")

        /** connect to MongoDB and get instance to work with */
        val uri = MongoClientURI(clusterAddress)
        val mongoSession = MongoClient(uri)
        val database = mongoSession.getDatabase(dataBaseName)
        val collection: MongoCollection<Document> = database.getCollection(collectionName)

        /** 1. get collection from Mongo */
        operationSystemTools.saveFromMongo(collection)
        // todo > make separate function in BasicOperation* interface

        // todo > PARSE JSON fields (delete object id, and other rubbish)

        /** 2. checked local library with MongoDB (WORD + WORD_COUNT++) */

        // todo > READ mongoWordArchive into collection
        // todo > COMPARE mongoWordArchive & filteredWords into UploadList

        /** 3. updateOne/Many database */

        // todo ADD UploadList to MongoDB
//        collection.updateOne(eq("WORD", "EARTH"), Document("\$set", Document("WORD", "EARTH-2")))
//        collection.updateOne(eq("WORD_COUNT", 1), Document("\$set", Document("WORD_COUNT", 2)))

        collection.updateMany(
            eq("WORD", "JUPITER"),
            combine(
                set("WORD_COUNT", 555)
            ));

        /** close connection to DB */
        mongoSession.close()
    }

}