package tools

import com.mongodb.MongoClient

/**
 * Connect to MongoDB cluster using URI
 * In Atlas add user in IP Whitelist (Network Access tab)
 * MongoDB Roles - atlasAdmin@admin , user - see
 */
interface DataBase {
    fun dataBaseConnect(){
    }
}
