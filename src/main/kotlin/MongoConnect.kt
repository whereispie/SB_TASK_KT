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

class MongoConnect : MongoConnect, BasicOperationSystemTools {

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

        /** 2. checked local library with MongoDB (WORD + WORD_COUNT++) */
        // todo > READ mongoWordArchive into collection
        // todo > COMPARE mongoWordArchive & filteredWords into UploadList

        /** 3. updateMany database */
        collection.updateMany(
            Filters.eq("WORD", "EARTH-2"),
            Updates.combine(
                Updates.set("WORD", "EARTH-13"),
                Updates.set("WORD_COUNT", 0)
            )
        );

        /** close connection to DB */
        mongoSession.close()
    }
}