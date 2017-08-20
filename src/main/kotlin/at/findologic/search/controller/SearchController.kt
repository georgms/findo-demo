package at.findologic.search.controller

import at.findologic.search.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(val searchService: SearchService) {

    @GetMapping("/search/{words}")
    fun findWords(@PathVariable words: String): List<String> {
        return searchService.searchWords(words.split(" "))
    }

    @GetMapping("/ac")
    fun autocomplete(@RequestParam(name = "query") query: String): List<String> {
        return if (!query.isBlank()) {
            searchService.autocomplete(query)
        } else {
            emptyList()
        }
    }
}