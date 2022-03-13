import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.request.UrlBuilder
import it.skrape.fetcher.request.UrlBuilder.Protocol.HTTPS
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.Doc
import it.skrape.selects.eachHref
import it.skrape.selects.eachLink
import it.skrape.selects.html5.*
import it.skrape.selects.text
import kotlinx.coroutines.runBlocking

/**
 * sometimes we want to handle data that is not available by an API but on websites ;)
 * But be careful, a practice like this has a moving target, since the website could change at any time and could thereby also break your implementation.
 */
class WebsiteScraper {

    fun getRwandaArticles(query: String = "news") = getRwandaArticleLinks()
        .filter { it.key.lowercase().contains(query.lowercase()) }
        .map { getArticle(it.value) }

    private val baseClient = skrape(HttpFetcher) {
        request {
            url {
                protocol = HTTPS
                host = "www.newtimes.co.rw"
                port = -1
            }
        }
    }

    private fun getRwandaArticleLinks(): Map<String, String> = runBlocking {
        baseClient.request { url { path = "/news/rwanda" } }
            .response {
                htmlDocument {
                    ".push-bundle .small-push > a" {
                        findAll {
                            eachLink
                        }
                    }
                }
            }
    }


    private fun getArticle(articleUrl: String) = runBlocking {
        baseClient.request { url { path = articleUrl } }
            .response {
                htmlDocument {
                    RwandaNewsArticle(
                        headline = getHeadline(),
                        picture = getPicture(),
                        content = getContent(),
                        source = baseClient.preparedRequest.url
                    )
                }
            }
    }

    private fun Doc.getHeadline() = h1 { 0 { text } }

    private fun Doc.getPicture() = ".article-media" {
        picture {
            source {
                0 { attribute("srcset") }
            }
        }
    }

    private fun Doc.getContent() = ".article-content" {
        0 {
            p { findAll { text } }
        }
    }
}

data class RwandaNewsArticle(
    val headline: String,
    val picture: String,
    val content: String,
    val source: String,
) {
    fun toArticle() = Article(
        title = headline,
        content = content,
        source = source
    )
}
