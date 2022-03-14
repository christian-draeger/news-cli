import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import okhttp3.Response
class CocktailClient(private val client: CocktailApiClient) {
    fun getCocktails(query: String = "margarita") : CocktailApiResponse {
        val responseBody = client.getRawCocktails(query).bodyAsString()
        return jacksonObjectMapper().readValue(responseBody)
    }
}
class CocktailApiClient {
    fun getRawCocktails(query: String = "margarita"): Response = httpGet {
        url("http://www.thecocktaildb.com/api/json/v1/1/search.php")
        param {
            "s" to query
        }
    }
}
fun Response.bodyAsString() = asString() ?: throw Exception("response not available")

@JsonIgnoreProperties(ignoreUnknown = true)
data class CocktailApiResponse(
    val status: String?,
    val totalResults: Int?,
    val drinks: List<Cocktail>
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Cocktail(
    val idDrink: String,
    val strDrink: String,
    val strCategory: String,
    val strAlcoholic: String,
    val strGlass: String,
    val strInstructions: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
)