import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Hello : CliktCommand() {
    val searchQuery by option(help = "what news you are interested in").prompt("search term")
    val filter by option(help = "filter the titles for a given string").prompt("filter title")
    val rwandaNewsSourceOnly by option(help = "filter the description values for a given string").prompt("rwanda news source only (yes | mix | no)")

    override fun run() {
        val articles = when (rwandaNewsSourceOnly) {
            "yes", "y" -> getRwandaNews()
            "mix", "mixed", "all" -> (getNewsFromNewsApi() + getRwandaNews()).shuffled()
            else -> getNewsFromNewsApi()
        }

        articles.filter { it.title.uppercase().contains(filter.uppercase()) }
            .asHtml()
            .storeAsFile()
            .also { echo("--> successfully stored articles to index.html") }
    }

    private fun getRwandaNews() = WebsiteScraper()
        .also { echo("## using rwanda news website scraper to fetch data") }
        .getRwandaArticles(searchQuery)
        .map { it.toArticle() }

    private fun getNewsFromNewsApi() = NewsClient(NewsApiClient())
        .also { echo("## using news api to fetch data") }
        .getNews(searchQuery)
        .articles.map { it.toArticle() }
}

fun main(args: Array<String>) = Hello().main(args)
