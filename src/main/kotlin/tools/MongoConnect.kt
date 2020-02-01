package tools

/**
 * Interface for DB connection
 * SECURITY ACCESS FROM ANOTHER HOST:
 *      Atlas add user in IP Whitelist (Network Access tab)
 *      MongoDB Roles - atlasAdmin@admin , user - see
 *      @dataBaseName - like in SQL
 *      @collectionName - table.SQl = collection.MongoDB
 * ELSE -> 0.0.0.0/0  all web
 */
interface MongoConnect {
    /**
     * Connect to MongoDB cluster using URI
     */
    fun dataBaseConnect(dataBaseName: String, collectionName: String) {
    }
}
