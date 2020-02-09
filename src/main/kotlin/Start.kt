import tools.BasicOperationSystemTools
import java.util.*

val init = LibraryCourier()
val tools = SystemTools()
val curator = LibraryCurator()
val operationSystemTools = SystemTools()
val config = Properties()


const val configurationFile = "config.properties"

open class Start : BasicOperationSystemTools {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            init.dataBaseConnect("library", "words") // TODO can i change on props ?
        }
    }
}