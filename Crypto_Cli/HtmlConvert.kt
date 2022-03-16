import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import java.io.File

fun Crypto.asHtml(): String = createHTMLDocument().div {
    h1 { +"CryptoLists :" }
        h3 {+ "Symbol:" }
        p { + symbol }
        br
}.serialize()

fun String.storeAsFile() {
    File("index.html").writeText(this)
}