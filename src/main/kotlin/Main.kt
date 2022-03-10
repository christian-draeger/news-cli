import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Hello: CliktCommand() {
    val input by option(help="what news you are interested in").prompt("search term")
    val filterDescription by option(help="filter the description values for a given string").prompt("filter description")

    override fun run() {
        NewsClient(NewsApiClient())
            .getNews(input)
            .articles
            .filter { it.description.uppercase().contains(filterDescription.uppercase()) }
            .asHtml()
            .storeAsFile()
    }
}

fun main(args: Array<String>) = Hello().main(args)