import com.google.gson.GsonBuilder
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
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

    override fun wordOperation(dataBaseName: String, collectionName: String) {

        /**
         * data. prepare data from configuration file to connect
         **/
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configurationFile
        config.load(FileInputStream(propertyFilePath))
        val clusterAddress = config.getProperty("URL_PARAM")

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
        val filePath = tools.chooseTextFile()
        val filteredUserBook = curator.rawFilter(filePath) // ArrayList //rawText.forEach { println(it) }
        filteredUserBook.forEach { println(it) }

        /**
         * 2.1 Create projection that excludes the _id field.
         **/

        val rawMongoBook = curator.saveFromMongo(collection) // ArrayList
        rawMongoBook.forEach { println(it) }
        /**
         * 2.2 Save to JSON and get WORD + COUNT
         **/
        val gson = GsonBuilder().setPrettyPrinting().create();
        FileWriter(File("src/main/resources/pretty.json")).use { writer -> gson.toJson(rawMongoBook, writer) }

        /**
         * 2.3 parse Mongo library into HashMap<word:String,count:Int>
         **/
        val filteredMongoBook = curator.jsonParser("WORD", "WORD_COUNT")
        filteredMongoBook.forEach { println(it) }

        /**
         * 3. compare inputFile_ArrayList & saveFromMongo.HashMap
         **/
        curator.bookEqualize(filteredUserBook, filteredMongoBook)

        /** 4. Update OR Insert into final HashMap */
        //todo
        /** 5. updateMany database in a LOOP into Mongo */
//        collection.updateMany(
//            Filters.eq("WORD", "EARTH-13"),
//            Updates.combine(
//                Updates.set("WORD", "EARTH-130"),
//                Updates.set("WORD_COUNT", 0)
//            )
//        );

        /** close connection to DB */
        mongoSession.close()
    }
}