import tools.BasicOperationSystemTools
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView
import kotlin.system.exitProcess

class SystemTools : BasicOperationSystemTools {
    override fun chooseTextFile(): String {
        val jfc = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
        val returnValue = jfc.showOpenDialog(null)
        var localOSFilePath = ""
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            val selectedFile = jfc.selectedFile
            localOSFilePath = selectedFile.absolutePath.toString()
        } else {
            println("[Please add filepath as absolute path, for example: /Users/see/resources/test.txt]")
            println("[Print EXIT to close application]")
            val readText = readLine()
            if (readText != null) {
                if (readText == "EXIT") exitProcess(0)
                if (readText.substringAfter(".").contains("txt") ||
                    readText.substringAfter(".").contains("doc") ||
                    readText.substringAfter(".").contains("odt") ||
                    readText.substringAfter(".").contains("pdf") ||
                    readText.substringAfter(".").contains("rtf") ||
                    readText.substringAfter(".").contains("tex")
                ) {
                    localOSFilePath = readText
                } else {
                    chooseTextFile()
                }
            }
        }
        return localOSFilePath
    }
}