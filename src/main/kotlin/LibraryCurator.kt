import tools.LibraryFilter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors

class LibraryCurator : LibraryFilter {
    override fun wordFilter(originalText: String): List<String> {
        return Files.readAllLines(Paths.get(originalText))
            .stream()
            .map { words: String -> words.split(" ".toRegex()).toTypedArray() }
            .flatMap { array: Array<String>? -> Arrays.stream(array) }
            .collect(Collectors.toList())
            .filter {
                it.matches("\\p{IsLatin}+".toRegex()) // Latin check
                        || it.matches("^\\p{IsCyrillic}+$".toRegex()) // Cyrillic check
                        || it.matches("[+-]?([0-9]*[.])?[0-9]+".toRegex()) // Integer check
                        || it.matches("[^\\d+\$]".toRegex()) // Float check
            }
    }
}