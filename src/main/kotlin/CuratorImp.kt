import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.DataBase
import java.io.FileInputStream
import java.util.*

class CuratorImp: DataBase {
    private val configFile = "config.properties"

    override fun dataBaseConnect() {
        // prepare data from configuration file to connect
        val config = Properties()
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configFile
        config.load(FileInputStream(propertyFilePath))
        val mongoClusterAddress = config.getProperty("URL_PARAM")
        // connect to MongoDB and get instance to work with
        val uri = MongoClientURI(mongoClusterAddress)
        val mongoClient = MongoClient(uri)
        // use db.${"your database"}
        val database = mongoClient.getDatabase("library")
        val collection: MongoCollection<Document> = database.getCollection("books")
        // add value to collection
        val person: Document = Document("title", "Diary of Bob from OFFLINE")
            .append("price", "21")
        // upload value to collection insertOne/ insertMany -> as a array[]
         collection.insertOne(person)
    }
}