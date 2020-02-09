package tools

import com.mongodb.client.MongoCollection
import org.bson.Document
import java.util.HashMap

/**
 * Interface for:
 * 1. Filter file for Latin,Cyrillic,digits else -> splitter
 * 2. Check data in DB for duplicate/or insert new WORD & WORD_COUNT ++
 * 3. Parse Json
 */

interface LibraryFilter {
    fun rawFilter(originalText: String): List<String> {
        return emptyList()
    }

    /**
     * Save data in JSON format from Mongo to local .txt file
     */
    fun saveFromMongo(collection: MongoCollection<Document>): MutableList<Document> {
        return arrayListOf()
    }

    /**
     * Parse data from JSON format from Mongo to String then HashMap
     */
    fun jsonParser(word: String, count: String): HashMap<String, Int>

    /**
     * Compare userBook and mongoBook
     */
    fun bookEqualize(userData: List<String>, mongoData: HashMap<String, Int>) {
    }
}
