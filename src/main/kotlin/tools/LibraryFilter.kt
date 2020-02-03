package tools

import java.io.File

/**
 * Interface for:
 * 1. Filter file for Latin,Cyrillic,digits else -> splitter
 * 2. Check data in DB for duplicate/or insert new WORD & WORD_COUNT ++
 */

interface LibraryFilter {
    fun wordFilter(originalText: String): List<String> {
        // new_word not in WORDS
        // then WORD = new_word, WORD_COUNT = 1
        // new_word in OLD_WORDS
        // then WORD_COUNT = 1
        return emptyList()
    }

    fun wordTableCheck(filteredFile: File) {}
}
