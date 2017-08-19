package at.findologic.search.service

import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.stereotype.Service
import java.util.HashMap
import java.util.regex.Pattern

@Service
class SearchService(val resourceLoader: ResourceLoader) {

    val invertedIndex: HashMap<String, HashSet<String>> = hashMapOf()

    /**
     * Indexes all .txt files found in path and creates the inverted index.
     *
     * @param   path    Location of .txt files.
     */
    fun indexFiles(path: String) {
        val wordSeperators = Pattern.compile("[^A-Za-z]")
        val files = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath*:$path")

        files.forEach {
            val file = it.file

            file.forEachLine { line ->
                val words = line.split(wordSeperators)

                words.forEach { word ->
                    val entry = invertedIndex.getOrDefault(word, hashSetOf())
                    entry.add(file.name)
                    invertedIndex.put(word, entry)
                }
            }
        }
    }

    /**
     * Searches the inverted index for words in the given list.
     *
     * @param words Search terms
     */
    fun searchWords(words: List<String>): List<String> {
        val documents = hashSetOf<String>()
        words.forEach { word ->
            val foundDocuments = invertedIndex.getOrDefault(word, hashSetOf())

            if (documents.isEmpty()) {
                documents.addAll(foundDocuments)
            } else {
                documents.retainAll(foundDocuments)
            }
        }
        return documents.toList()
    }

    fun lookup(query: String) = searchWords(query.split(" "))

    /**
     * Method replaces last (partial) word in the query with all possible candidates
     * and performs a lookup. If the lookup is successfull the query is added to the
     * results.
     *
     * @param query Given query containing possible words to look for
     * @return List containing all possible search terms which are non-empty
     */
    fun autocomplete(query: String): List<String> {
        val words = query.split(" ")
        val results = arrayListOf<String>()

        val lastWord = words.last()

        invertedIndex.keys.forEach {
            if (it.startsWith(lastWord)) {
                val lookingFor = words.subList(0, words.lastIndex).plus(it)

                if (searchWords(lookingFor).isNotEmpty()) {
                    results.add(lookingFor.joinToString(" "))
                }
            }
        }

        return results
    }
}