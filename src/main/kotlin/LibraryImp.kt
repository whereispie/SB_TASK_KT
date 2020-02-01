import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.BasicCRUD
import tools.Curator
import tools.DataBase
import java.io.FileInputStream
import java.util.*


class LibraryImp : DataBase, BasicCRUD, Curator {
    private val configFile = "config.properties"

    override fun dataBaseConnect(dataBaseName: String, collectionName: String) {
        // prepare data from configuration file to connect
        val config = Properties()
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configFile
        config.load(FileInputStream(propertyFilePath))
        val mongoClusterAddress = config.getProperty("URL_PARAM")
        // connect to MongoDB and get instance to work with
        val uri = MongoClientURI(mongoClusterAddress)
        val mongoClient = MongoClient(uri)
        val database = mongoClient.getDatabase(dataBaseName)
        val collection: MongoCollection<Document> = database.getCollection(collectionName)

        // 1. get collection from Mongo
        // 2. convert to Java collection (HashMap?)
        // 3. update database
        val words: Document = Document("WORD", "Bumperzz")
            .append("WORD_COUNT", 1)

        // update
        update(collection, words)

        // close connection to DB
        mongoClient.close()
    }
}