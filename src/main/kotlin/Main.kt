import tools.BasicOperationSystemTools

open class Main : BasicOperationSystemTools {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val start = MongoConnect()
//            val choose = SystemTools()
//            val wordFilter = Curator()
//            val filePath = choose.chooseTextFile()
//            val filteredWords = wordFilter.wordFilter(filePath)
//            filteredWords.forEach { println(it) }
            start.dataBaseConnect("library","words") // TODO replace on config.file variables
        }
    }
}