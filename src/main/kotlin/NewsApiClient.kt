import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import okhttp3.Response

class NewsClient(private val client: NewsApiClient) {
    fun getNews(query: String = "news"): NewsApiResponse {
        val responseBody = client.getRawNews(query).bodyAsString()
        return jacksonObjectMapper().readValue(responseBody)
    }
}

class NewsApiClient {
    fun getRawNews(query: String = "news"): Response = httpGet {
        url("https://newsapi.org/v2/everything")
        param {
            "q" to query
        }
        header {
            "Authorization" to "ca09ea8ee8e64e6f9bf3d18f0cea6d67"
        }
    }
}

fun Response.bodyAsString() = asString() ?: throw Exception("response not available")

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsApiArticle>
)

data class NewsApiArticle(
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val source: NewsApiArticleSource,
)

data class NewsApiArticleSource(
    val id: String?,
    val name: String,
)
