package at.findologic.search.frontend

import at.findologic.search.service.SearchService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class FrontendController(val searchService: SearchService) {

    @RequestMapping("/")
    fun home(model: Model, @RequestParam(name = "query", required = false) query: String?): String {
        model.addAttribute("query", query)
        if (query != null)
            model.addAttribute("results", searchService.lookup(query))
        else
            model.addAttribute("results", listOf<String>())
        return "home"
    }
}