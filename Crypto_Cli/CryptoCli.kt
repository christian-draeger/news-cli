import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import okhttp3.Response

class CryptoClient(private val client: CryptoApiClient) {
    fun getCrypto(query: String = "btcinr") : List<Crypto> {
        val responseBody = client.getRawCrypto(query).bodyAsString()
        return jacksonObjectMapper().readValue(responseBody)
    }
}
class CryptoApiClient {
    fun getRawCrypto(query: String = "btcinr"): Response = httpGet {
        url("https://api.wazirx.com/sapi/v1/tickers/24hr")
        param {
            "symbol" to query
        }
    }
}
    fun Response.bodyAsString() = asString() ?: throw Exception("response not available")

@JsonIgnoreProperties(ignoreUnknown = true)
data class Crypto(
        val symbol: String,
        val baseAsset: String,
        val quoteAsset: String,
        val openPrice: String,
        val lowPrice: String,
        val highPrice: String,
        val lastPrice: String,
        val volume: String,
        val bidPrice: String,
        val askPrice: String,
        )