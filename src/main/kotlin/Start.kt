import tools.BasicOperationSystemTools
import java.util.*
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.AbstractApplicationContext
import tools.MongoConnect


val config = Properties()
val curator = LibraryCurator()
val tools = SystemTools()
const val configurationFile = "config.properties"

open class Start : BasicOperationSystemTools {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val filePath = tools.chooseTextFile()
            val context: AbstractApplicationContext =
                AnnotationConfigApplicationContext(MongoConfiguration::class.java) as AbstractApplicationContext
            val greetingService: MongoConnect = context.getBean(MongoConnect::class.java)
            greetingService.wordOperation(filePath)

            context.registerShutdownHook()
        }
    }
}