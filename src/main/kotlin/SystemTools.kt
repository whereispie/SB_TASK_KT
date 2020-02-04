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

    override fun saveToLocalFromMongo(collection: MongoCollection<Document>) {
        var cursor: MongoCursor<Document?>? = collection.find().iterator()
        val mongoWordArchive = "/Users/see/IdeaProjects/SB_TASK_KT/src/main/resources/uploadFromMongo.txt"
        val out = PrintWriter(
            BufferedWriter(
                FileWriter(
                    mongoWordArchive,
                    true
                )
            ) // todo > CHANGE to lambda function FileWriter("c:\\test\\staff.json").use { writer -> gson.toJson(projectBeans, writer) }
        )
        while (cursor!!.hasNext()) {
            out.println(cursor.next()?.toJson())
        }
        out.flush() // flush to ensure writes
        out.close()
    }
}