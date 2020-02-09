import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.BasicOperationSystemTools
import tools.MongoConnect
import java.io.FileInputStream


/**
 * Class for: connect -> filter input file -> get vocabulary from Mongo -> compare words -> update/add new
 */
class LibraryCourier : MongoConnect, BasicOperationSystemTools {

    override fun dataBaseConnect(dataBaseName: String, collectionName: String) {

        /** prepare data from configuration file to connect */
        val path = Thread.currentThread().contextClassLoader.getResource("")?.path
        val propertyFilePath = path + configurationFile
        config.load(FileInputStream(propertyFilePath))
        val clusterAddress = config.getProperty("URL_PARAM")
        /** connect to MongoDB and get instance to work with */
        val uri = MongoClientURI(clusterAddress)
        val mongoSession = MongoClient(uri)
        val database = mongoSession.getDatabase(dataBaseName)
        val collection: MongoCollection<Document> = database.getCollection(collectionName)
        /** 1) parse inputFile into ArrayList<String> */
//        val filePath = tools.chooseTextFile()
//        val inputFile = curator.inputFileFilter(filePath) // toArrayList OK
//        inputFile.forEach { println(it) }
        /** 2) GET ALL Mongo data from Cloud */
//        val mongoFilter = operationSystemTools.saveFromMongo(collection)
        
        /** 2.1.2) parse Mongo library into List */
        val parseMongo = curator.mongoFilter("src/main/resources/uploadFromMongo.txt")
        parseMongo.forEach { println(it) }
        //todo parse JSON
        /** 2.1.2) parse Mongo library into HashMap<word:String,count:Int> */
        //todo put into HashMap
        /** 3. compare inputFile_ArrayList & saveFromMongo.HashMap */
        //todo
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
        //todo
        /** Map example */
//                Map<String, Object> documentMap = new HashMap<String, Object>();
//        documentMap.put("database", "mkyongDB");
//        documentMap.put("table", "hosting");
//
//        Map<String, Object> documentMapDetail = new HashMap<String, Object>();
//        documentMapDetail.put("records", 99);
//        documentMapDetail.put("index", "vps_index1");
//        documentMapDetail.put("active", "true");
//
//        documentMap.put("detail", documentMapDetail);
//
//        collection.insert(new BasicDBObject(documentMap));

//        var map: MutableMap<Any?, Any?>? = HashMap<Any, Any>()
//        map!!["1"] = "Department A"
//        map["2"] = "Department B"
//        collection.insert(BasicDBObject(map))


        /** close connection to DB */
        mongoSession.close()
    }
}