import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document
import tools.DataBase
import java.io.FileInputStream
import java.util.*


open class Library {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val start = CuratorImp()
            start.dataBaseConnect()
        }
    }
}