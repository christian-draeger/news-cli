import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import java.io.File

fun Activity.asHtml(): String = createHTMLDocument().div {
    h1 { +"ActivityLists :" }
    h3 {+ "activity:" }
    p { + activity }
    br
    h3 {+ "accessibility:" }
    p { + accessibility }
    br
    h3 {+ "type:" }
    p{+ type}
    br
    h3 {+ "participants:" }
    p{+ participants}
    br
    h3 {+ "price:" }
    p{+ price}
    br
    h3 {+ "link:" }
    p{+ link}
    h3 {+ "key:" }
    p{+ key}
    br
}.serialize()

fun String.storeAsFile() {
    File("index.html").writeText(this)
}