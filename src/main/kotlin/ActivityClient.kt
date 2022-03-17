import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import okhttp3.Response

class ActivityClient (private val activity: ActivityApiClient){
    fun getActivity(query: String = "activity") : ActivityResponse {
        val responseBody = activity.getRawActivity().bodyAsString()
        return jacksonObjectMapper().readValue(responseBody)
    }
}

class ActivityApiClient{
    fun getRawActivity(query: String = "activity"): Response = httpGet {
        url("https://www.boredapi.com/api/activity")
        param {
            "act" to query
        }
    }
}
    fun Response.bodyAsString() = asString() ?: throw Exception("response not available")

@JsonIgnoreProperties(ignoreUnknown = true)
data class ActivityResponse(
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: String,
    val link: String,
    val key: String,
)
