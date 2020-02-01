package tools

/**
 * Interface for DB connection
 * ACCESS FROM ANOTHER HOST:
 *      Atlas add user in IP Whitelist (Network Access tab)
 *      MongoDB Roles - atlasAdmin@admin , user - see
 *      @dataBaseName - like in SQL
 *      @collectionName - table.SQl = collection.MongoDB
 */
interface DataBase {
    /**
     * Connect to MongoDB cluster using URI
     */
    fun dataBaseConnect(dataBaseName: String, collectionName: String) {
    }
}
