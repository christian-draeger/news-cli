import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.w3c.dom.Document
import java.io.File

fun List<NewsApiArticle>.asHtml(): String = createHTMLDocument().div {
    h1 { +"latest news" }
    forEach {
        h3 { +it.title }
        h4 { +it.description }
        div { +it.content }
        br
    }
}.serialize()

fun String.storeAsFile() {
    File("index.html").writeText(this)
}
