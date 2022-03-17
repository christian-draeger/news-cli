import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import java.io.File

fun Crypto.asHtml(): String = createHTMLDocument().div {
    h1 { +"CryptoLists :" }
       h3 {+ "Symbol:" }
        p { + symbol }
        br
        h3 {+ "BaseAsset:" }
        p { + baseAsset }
        p { + quoteAsset }
        br
        h3 {+ "OpenPrice:" }
        p{+ openPrice}
        br
        h3 {+ "LowPrice:" }
        p{+ lowPrice}
        br
        h3 {+ "HighPrice:" }
        p{+ highPrice}
        br
        h3 {+ "LastPrice:" }
        p{+ lastPrice}
        h3 {+ "volume:" }
        p{+ volume}
        br
        h3 {+ "BidPrice:" }
        p{+ bidPrice}
        br
        h3 {+ "AskPrice:" }
        p{+ askPrice}
        br
}.serialize()

fun String.storeAsFile() {
    File("index.html").writeText(this)
}
