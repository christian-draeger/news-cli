import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Hello: CliktCommand() {
    val input by option(help="What cocktail whose contents you want to see?").prompt("search cocktail ")
    override fun run() {
        CocktailClient(CocktailApiClient())
            .getCocktails(input)
            .drinks
            .asHtml()
            .storeAsFile()
    }
}

fun main(args: Array<String>) = Hello().main(args)