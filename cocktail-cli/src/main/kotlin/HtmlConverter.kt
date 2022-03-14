import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import java.io.File

fun List<Cocktail>.asHtml(): String = createHTMLDocument().div {
        h1 { +"cocktails :" }
        forEach {
            h2 { +it.strDrink }
            h3 {+ "Ingredients:" }
            p { +it.strIngredient1 }
            p{+","}
            p{+it.strIngredient2}
            p{+","}
            p{+it.strIngredient3}
            br
            h3 {+ "Instructions to make the cocktail:" }
            br
            div { +it.strInstructions }
            br
        }
    }.serialize()

    fun String.storeAsFile() {
        File("index.html").writeText(this)
    }
