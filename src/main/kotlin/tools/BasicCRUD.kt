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
     * @MongoCollection - Collections are analogous to tables in relational databases.
     * @Document - Data in wrap of JSON Object
     */

    fun insertOne()

    fun insertMany()

    fun addWord()

}