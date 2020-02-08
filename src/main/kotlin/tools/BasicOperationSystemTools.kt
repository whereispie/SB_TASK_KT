package tools

import com.mongodb.client.MongoCollection
import org.bson.Document

/**
 * Interface helps manipulate OS GUI
 */

interface BasicOperationSystemTools {

    /**
     * Choose file with text source using JChooser
     */

    fun chooseTextFile(): String {
        return toString()
    }

    /**
     * Save data in JSON format from Mongo to local .txt file
     */
    fun saveFromMongo(collection: MongoCollection<Document>) {
    }
}