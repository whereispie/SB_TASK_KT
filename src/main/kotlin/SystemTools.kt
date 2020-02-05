import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoCursor
import org.bson.Document
import tools.BasicOperationSystemTools
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView
import kotlin.system.exitProcess

class SystemTools : BasicOperationSystemTools {
    override fun chooseTextFile(): String {
        val jfc = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
        val returnValue = jfc.showOpenDialog(null)
        var localFilePath = ""
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            val selectedFile = jfc.selectedFile
            localFilePath = selectedFile.absolutePath.toString()
        } else {
            println("[Please add filepath as absolute path, for example: /Users/see/resources/test.txt]")
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
        }
        return localFilePath
    }

    override fun saveFromMongo(collection: MongoCollection<Document>) {
        val cursor: MongoCursor<Document?>? = collection.find().iterator()
        val libraryArchive = "/Users/see/IdeaProjects/SB_TASK_KT/src/main/resources/uploadFromMongo.txt"
        val fileCleaner = PrintWriter(libraryArchive)
        fileCleaner.print("")
        fileCleaner.close()
        val out = PrintWriter(
            BufferedWriter(
                FileWriter(
                    libraryArchive,
                    true
                )
            ) // todo > CHANGE to lambda function FileWriter("c:\\test\\staff.json").use { writer -> gson.toJson(collection, writer) }
        )
        while (cursor!!.hasNext()) {
            out.println(cursor.next()?.toJson())
        }
        out.flush()
        out.close()
    }
}