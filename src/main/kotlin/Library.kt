open class Library {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val start = LibraryImp()
            val sets = start.wordFilter("src/main/resources/test.txt")
            sets.forEach { println(it) }
            start.dataBaseConnect("library","words")
        }
    }
}