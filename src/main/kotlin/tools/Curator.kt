package tools

import java.io.File

interface Curator {
    fun wordFilter(originalFile: File) {}
    fun wordTableCheck(filteredFile: File) {}
}
