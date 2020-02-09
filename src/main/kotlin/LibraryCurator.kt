import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Projections
import org.bson.Document
import tools.LibraryFilter
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors


class LibraryCurator : LibraryFilter {
    override fun rawFilter(originalText: String): List<String> {
        return Files.readAllLines(Paths.get(originalText)) // readAllLines - StandardCharsets.UTF_8 format
            .stream()
            .map { words: String ->
                words.split("[ {/,;:!@#\$%^&*()_+\\-=} ]".toRegex()) // Regex check SPACE before/after + ALL common symbols around the WORDS
                    .toTypedArray()
            }
            .flatMap { array: Array<String>? -> Arrays.stream(array) }
            .collect(Collectors.toList())
            .filter {
                it.matches("\\p{IsLatin}+\$".toRegex()) // Latin check
                        || it.matches("^\\p{IsCyrillic}+\$".toRegex()) // Cyrillic check
                        || it.matches("[+-]?([0-9]*[.])?[0-9]+".toRegex()) // Integer + Float check
            }
    }

    /** 2.1.1) Creates a projection that excludes the _id field. */
    override fun saveFromMongo(collection: MongoCollection<Document>): MutableList<Document> {
        val cursor = collection.find().projection(Projections.excludeId())
        val asList: MutableList<Document> = ArrayList()
        cursor.forEach(Consumer { document: Document -> asList.add(document) })
        return asList
    }

    /** 2.1.1) Creates a projection that excludes the _id field. */
    override fun jsonParser(word: String, count: String): HashMap<String, Int> {
        val parser = JsonParser()
        val obj = parser.parse(FileReader(File("src/main/resources/pretty.json")))
        val jsonObjects: JsonArray = obj as JsonArray
        val documentMap = HashMap<String, Int>()
        for (o in jsonObjects) {
            val jsonObject: JsonObject = o as JsonObject
            val word = jsonObject.get(word).toString().substringAfter("\"").substringBefore("\"")
            print("$word=")
            val wordCount = jsonObject.get(count).toString()
            println(wordCount)
            val wordCountToInt = wordCount.toInt()
            documentMap[word] = wordCountToInt
        }
        println(documentMap)
        return documentMap
    }
}
