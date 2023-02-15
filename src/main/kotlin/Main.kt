import model.News
import okhttp3.OkHttpClient
import okhttp3.Request
import parser.GsonParser
import parser.JsonSimpleParser
import parser.XmlParser
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


private const val ENDPOINT = "https://api2.kiparo.com/"
private const val JSON_URI = "static/it_news.json"
private const val XML_URI = "static/it_news.xml"

fun main() {
    var resultStr = ""
    println(
        "Нажмите 1 что-бы скачать JSON используя UrlConnection\n" +
                "Нажмите 2 что-бы скачать JSON используя OkHttp\n" +
                "Нажмите 3 что -бы скачать XML используя UrlConnection\n" +
                "Нажмите 4 что -бы скачать XML используя OkHttp"
    )
    val modeChoice = (readlnOrNull() ?: "0")
    when (modeChoice) {
        "1" -> {
            resultStr = getStrFromKiparoUseUrlConnection(JSON_URI)
        }

        "2" -> {
            resultStr = getStrFromKiparoUseOkHttp(JSON_URI)
        }

        "3" -> {
            resultStr = getStrFromKiparoUseUrlConnection(XML_URI)
        }

        "4" -> {
            resultStr = getStrFromKiparoUseOkHttp(XML_URI)
        }
    }
    val listNews: List<News>
    if (modeChoice in "1".."2") {
        println("Введите 1-если хотите использовать JsonSimpleParser, 2- eсли Gson")
        listNews = when ((readlnOrNull() ?: "0")) {
            "1" -> JsonSimpleParser().parse(resultStr).newsList
            "2" -> GsonParser().parse(resultStr).newsList
            else -> {
                emptyList()
            }
        }
    } else if (modeChoice in "2".."3") {
        listNews = XmlParser().parse(resultStr).newsList
    } else listNews = emptyList()
    //Здесь получается лапша , комбинаторный взрыв , понимаю что надо применить какой нибудь паттерн вроде ДЕКОРАТОР но не хватает знаний
    //что бы его реализовать

    var continueQuestion = true
    do {
        println(
            "\n\nНажмите 1 чтобы вывести новости по дате и вывести их на экран\n" +
                    "Нажмите 2 что-бы вывести даты новостей в порядке свежести\n" +
                    "Нажмите 3 что-бы вывести title новостей \n" +
                    "Нажмите 4 для поиска новостей  по ключевым словам\n" +
                    "Нажмите 5 для выхода\n"
        )
        //if (questionNumber == "5") break
        when ((readlnOrNull() ?: "0")) {
            "1" ->
                listNews.sortedByDescending { it.date }
                    .forEach { print("${it.description}\n\n") }

            "2" -> listNews.sortedByDescending { it.date }
                .forEach { println(it.date) }

            "3" -> listNews.forEach { println(it.title) }
            "4" -> findForKeywords(listNews).forEach { println(it.description) }
            "5" -> break
        }
        println("\nЕсли хотите продолжить нажмите любую кнопку. Если нужно выдти нажмите 5")
        if ((readlnOrNull() ?: "0") == "5") continueQuestion = false

    } while (continueQuestion)
    println("The program is done")
}

fun findForKeywords(list: List<News>): List<News> {
    println("Введите ключевое слово по которому вы хотите вывести новость.\n")
    val key = readlnOrNull() ?: "0"
    return list.filter { news: News -> news.keywords.any { it == key } }
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
    println("Used UrlConnection")
    return text
}

fun getStrFromKiparoUseOkHttp(str: String): String {
    val okHttp = OkHttpClient()
    val request: Request = Request.Builder()
        .url(ENDPOINT + str)
        .build()
    okHttp.newCall(request).execute().use { response ->
        println("Used Okhttp")
        return response.body?.string() ?: "Error"
    }
}


