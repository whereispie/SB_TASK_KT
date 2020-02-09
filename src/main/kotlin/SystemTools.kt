import tools.BasicOperationSystemTools
import kotlin.system.exitProcess

class SystemTools : BasicOperationSystemTools {
    override fun chooseTextFile(): String {
        var localFilePath = ""
        println("[Please ADD file]")
        println("[UNIX: /Users/see/FOR_EXAMPLE.txt]")
        println("""[WINDOWS: C:\resources\FOR_EXAMPLE.txt]""")
        println("[Print EXIT to close application]")
        val consoleInput = readLine()
        if (consoleInput != null) {
            if (consoleInput == "EXIT") exitProcess(0)
            if (consoleInput.substringAfter(".").contains("txt") ||
                consoleInput.substringAfter(".").contains("doc") ||
                consoleInput.substringAfter(".").contains("odt") ||
                consoleInput.substringAfter(".").contains("pdf") ||
                consoleInput.substringAfter(".").contains("rtf") ||
                consoleInput.substringAfter(".").contains("tex")
            ) {
                localFilePath = consoleInput
            } else {
                chooseTextFile()
            }
        }
        return localFilePath
    }
}