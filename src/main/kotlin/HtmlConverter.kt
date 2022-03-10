import java.io.File

fun String.storeAsFile() {
    File("/build/index.html").writeText(this)
}
