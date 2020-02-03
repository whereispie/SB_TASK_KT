package tools

import com.mongodb.client.MongoCollection
import org.bson.Document

/**
 * Connect to MongoDB cluster using URI
 * In Atlas add user in IP Whitelist (Network Access tab)
 * MongoDB Roles - atlasAdmin@admin , user - see
 */
interface BasicCRUD {
    /**
     * UPDATE collection with current values from document
     */
    fun insertOne(mongoCollection: MongoCollection<Document>, words: Document) {
        mongoCollection.insertOne(words)
    }
}