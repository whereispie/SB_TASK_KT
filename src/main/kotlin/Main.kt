import tools.BasicOperationSystemTools

val initial = MongoConnect()
val tools = SystemTools()
val curator = LibraryCurator()

open class Main : BasicOperationSystemTools {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val filePath = tools.chooseTextFile()
            val inputFile = curator.fileFilter(filePath)
            inputFile.forEach { println(it) }
            initial.dataBaseConnect("library","words") // TODO replace on config.file variables
        }
    }
}