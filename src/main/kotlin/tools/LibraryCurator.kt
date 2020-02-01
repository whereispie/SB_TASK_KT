package tools

import java.io.File

/**
 * Interface for:
 * 1. Filter file for Latin,Cyrillic,digits else -> splitter
 * 2. Check data in DB for duplicate/or insert new WORD & WORD_COUNT ++
 */

interface LibraryCurator {
    fun wordFilter(originalFile: String): Set<String> {
        // new_word not in WORDS
        // then WORD = new_word, WORD_COUNT = 1
        // new_word in OLD_WORDS
        // then WORD_COUNT = 1
        return emptySet()
    }

    fun wordTableCheck(filteredFile: File) {}
}
