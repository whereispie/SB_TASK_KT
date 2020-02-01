package tools

import java.io.File

/**
 * Interface for:
 * 1. Filter file for Latin,Cyrillic,digits else -> splitter
 * 2. Check data in DB for duplicate/or insert new WORD & WORD_COUNT ++
 */

interface Curator {
    fun wordFilter(originalFile: File) {
        // 1. get collection from Mongo
        // 2. convert to Java collection (HashMap?)
        // 3. update database
    }
    fun wordTableCheck(filteredFile: File) {}
}
