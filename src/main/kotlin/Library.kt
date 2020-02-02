open class Library {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val start = LibraryImp()
            val filteredWords = start.wordFilter("src/main/resources/test.txt")
            filteredWords.forEach { println(it) }
            start.dataBaseConnect("library","words")
        }
    }
}