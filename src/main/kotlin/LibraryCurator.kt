import tools.LibraryFilter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors

class LibraryCurator : LibraryFilter {
    override fun fileFilter(originalText: String): List<String> {
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
}