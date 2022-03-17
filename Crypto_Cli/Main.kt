import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Hello: CliktCommand() {
    val input by option(help="Today's info Crypto Update?").prompt("search For Crypto")
    override fun run() {
        CryptoClient(CryptoApiClient())
            .getCrypto(input).first()
            .asHtml()
            .storeAsFile()
    }
}

fun main(args: Array<String>) = Hello().main(args)