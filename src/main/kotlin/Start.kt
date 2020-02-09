import tools.BasicOperationSystemTools
import java.util.*

val config = Properties()
val init = LibraryCourier()
val curator = LibraryCurator()
val tools = SystemTools()
const val configurationFile = "config.properties"

open class Start : BasicOperationSystemTools {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            init.wordOperation("library", "words")
        }
    }
}