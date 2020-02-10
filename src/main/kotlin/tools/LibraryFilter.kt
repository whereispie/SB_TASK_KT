package tools

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import org.bson.Document
import kotlin.collections.HashMap

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
    fun bookEqualize(
        userData: List<String>,
        mongoData: HashMap<String, Int>,
        toUpdateMongoBook: HashMap<String, Int>
    ) {
    }


    /**
     * updateMany database in a LOOP into Mongo //todo make JSON [] update
     */
    fun updateWords(oldCollection: MongoCollection<Document>, newCollection: HashMap<String, Int>) {
        for ((key, value) in newCollection) {
            oldCollection.updateMany(
                Filters.eq("WORD", key),
                Updates.combine(
                    Updates.set("WORD", key),
                    Updates.set("WORD_COUNT", value)
                )
            )
        }
    }
}
