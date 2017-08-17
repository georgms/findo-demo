package at.findologic.search.controller

import at.findologic.search.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchController(val searchService: SearchService) {

    @GetMapping("/{words}")
    fun findWords(@PathVariable words: String): List<String> {
        return searchService.searchWords(words.split(" "))
    }

    @GetMapping("/ac/{query}")
    fun autocomplete(@PathVariable query: String) : List<String> {
        return searchService.autocomplete(query)
    }
}