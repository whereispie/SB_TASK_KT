import tools.BasicOperationSystemTools

open class Library:BasicOperationSystemTools {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val start = LibraryImp()
            val filePath = start.chooseTextFile()
            val filteredWords = start.wordFilter(filePath)
            filteredWords.forEach { println(it) }
//            start.dataBaseConnect("library","words")
        }
    }
}