package at.findologic.search

import at.findologic.search.service.SearchService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SearchApplication(val searchService: SearchService) : CommandLineRunner {
    override fun run(vararg p0: String?) {
        searchService.indexFiles("files/wiki/*.txt")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SearchApplication::class.java, *args)
}