import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Hello : CliktCommand() {
val input by option(help="Activity recommended to you").prompt("Look for activity")
    override fun run() {
        ActivityClient(ActivityApiClient())
            .getActivity(input).first()
            .asHtml()
            .storeAsFile()
    }

}

fun main(args: Array<String>) = Hello().main(args)
