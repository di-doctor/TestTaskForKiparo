import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


private const val ENDPOINT = "https://api2.kiparo.com/"
private const val JSON_URI = "static/it_news.json"
private const val XML_URI = "static/it_news.xml"
private val TYPE_PARS = TypeParsing.JSON_SIMPLE     //тип парсинга




fun main(args: Array<String>) {
    var resultStr = ""
    println(
        "Нажмите 1 что-бы скачать JSON используя UrlConnection\n" +
                "Нажмите 2 что-бы скачать JSON используя OkHttp\n" +
                "Нажмите 3 что -бы скачать XML используя UrlConnection\n" +
                "Нажмите 4 что -бы скачать XML используя OkHttp"
    )
    val modeChoice = (readlnOrNull() ?: "0")
    if (modeChoice == "1") {
        resultStr = getStrFromKiparoUseUrlConnection(JSON_URI)
    } else if (modeChoice == "2") {
        resultStr = getStrFromKiparoUseOkHttp(JSON_URI)
    } else if (modeChoice == "3") {
        resultStr = getStrFromKiparoUseUrlConnection(XML_URI)
    } else if (modeChoice == "4") {
        resultStr = getStrFromKiparoUseOkHttp(XML_URI)
    }
    println(JsonSimpleParser().parse(resultStr))
    //println(resultStr)

}

fun getStrFromKiparoUseUrlConnection(str: String): String {
    val httpURLConnection = URL(ENDPOINT + str).openConnection() as HttpURLConnection
    httpURLConnection.apply {
        connectTimeout = 10000 //10 секунд таймаут
        requestMethod = "GET"
        doInput = true
    }
    if (httpURLConnection.responseCode != HttpURLConnection.HTTP_OK)
        return "Download error"
    val streamReader = InputStreamReader(httpURLConnection.inputStream)
    var text = ""
    try {
        streamReader.readText().also { text = it }
    } catch (e: IOException) {
        println("Read Error")
    } finally {
        streamReader.close()
    }
    println("Work UrlConnection")
    return text
}

fun getStrFromKiparoUseOkHttp(str: String): String {
    val okHttp = OkHttpClient()
    val request: Request = Request.Builder()
        .url(ENDPOINT + str)
        .build()
    okHttp.newCall(request).execute().use { response ->
        println("Okhttp -work")
        return response.body?.string() ?: "Error"
    }
}


