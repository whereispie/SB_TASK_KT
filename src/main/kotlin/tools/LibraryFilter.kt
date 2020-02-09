package tools

/**
 * Interface for:
 * 1. Filter file for Latin,Cyrillic,digits else -> splitter
 * 2. Check data in DB for duplicate/or insert new WORD & WORD_COUNT ++
 */

interface LibraryFilter {
    fun inputFileFilter(originalText: String): List<String> {
        return emptyList()
    }

    fun mongoFilter(originalText: String): List<String> {
        return emptyList()
    }
}
