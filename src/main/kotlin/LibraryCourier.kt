import com.google.gson.GsonBuilder
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import org.bson.Document
import tools.BasicOperationSystemTools
import tools.MongoConnect
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter


/**
 * Class for: connect -> filter input file -> get vocabulary from Mongo -> compare words -> update/add new
 */
class LibraryCourier : MongoConnect, BasicOperationSystemTools {

    override fun wordOperation(filePath: String) {

        /**
         * data. prepare data from configuration file to connect
         **/
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configurationFile
        config.load(FileInputStream(propertyFilePath))
        val clusterAddress = config.getProperty("URL_PARAM")
        val dataBaseName = config.getProperty("DB_NAME")
        val collectionName = config.getProperty("COLLECTION")

        /**
         * init. connect to MongoDB and get instance to work with
         * */
        val uri = MongoClientURI(clusterAddress)
        val mongoSession = MongoClient(uri)
        val database = mongoSession.getDatabase(dataBaseName)
        val collection: MongoCollection<Document> = database.getCollection(collectionName)

        /**
         * 1. parse inputFile into ArrayList<String>
         **/
        val filteredUserBook = curator.rawFilter(filePath) // ArrayList //rawText.forEach { println(it) }
//        filteredUserBook.forEach { println(it) }

        /**
         * 2.1 Create projection that excludes the _id field.
         **/

        val rawMongoBook = curator.saveFromMongo(collection) // ArrayList
//        rawMongoBook.forEach { println(it) }
        /**
         * 2.2 Save to JSON and get WORD + COUNT
         **/
        val gson = GsonBuilder().setPrettyPrinting().create();
        FileWriter(File("src/main/resources/pretty.json")).use { writer -> gson.toJson(rawMongoBook, writer) }

        /**
         * 2.3 parse Mongo library into HashMap<word:String,count:Int>
         **/
        val filteredMongoBook = curator.jsonParser("WORD", "WORD_COUNT")
//        filteredMongoBook.forEach { println(it) }

        /**
         * 3. compare inputFile_ArrayList & saveFromMongo.HashMap
         **/
        val toUpdateMongoBook = HashMap<String, Int>()
        curator.bookEqualize(filteredUserBook, filteredMongoBook, toUpdateMongoBook)
        println("[UserBook]")
        filteredUserBook.forEach { println(it) }
        println("***")
        println("[MongoBook]")
        filteredMongoBook.forEach { println(it) }
        println("***")
        println("[toUpdateMongoBook]")
        toUpdateMongoBook.forEach { println(it) }

        /** 4. updateMany database in a LOOP into Mongo */
        curator.updateWords(collection, filteredMongoBook)

        /** close connection to DB */
        mongoSession.close()
    }
}