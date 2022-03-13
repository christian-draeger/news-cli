import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.intellij.lang.annotations.Language
import org.w3c.dom.Document
import java.io.File
import javax.management.Query.div

@Language("CSS")
private val css = """
    body {
        background-color: #272727;
        color: #ccc;
        font-family: SANS-SERIF;
        padding: 20px 60px;
    }
    h1 {
        text-align: center;
    }
    a {
        color: beige;
    }
    .article {
        border: 5px solid grey;
        padding: 30px;
        border-radius: 25px;
        background-color: #373737;
    }
""".trimIndent()

fun List<Article>.asHtml(): String = createHTMLDocument().html {
    head {
        style { unsafe { raw( css) } }
    }
    body {
        div {
            h1 { +"latest news" }
            forEach {
                div("article") {
                    a {
                        href = it.source
                        target = "_blank"
                        h3 { +it.title }
                    }
                    it.description?.let {
                        h4 { +it }
                    }
                    p { +it.content }
                }
                br
            }
            if (isEmpty()) {
                h3 { +"no matching news found" }
            }
        }
    }
}.serialize()


    fun String.storeAsFile() {
        File("index.html").writeText(this)
    }

    data class Article(
        val title: String,
        val description: String? = null,
        val content: String,
        val source: String
    )
