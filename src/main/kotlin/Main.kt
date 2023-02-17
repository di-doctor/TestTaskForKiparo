import InternetService.ApplyData
import InternetService.JSON_URI
import InternetService.XML_URI
import model.News
import parser.GsonParser
import parser.JsonSimpleParser
import parser.XmlParserJsoup

fun main() {
    println(
        "Нажмите 1 что-бы скачать JSON используя UrlConnection\n" +
                "Нажмите 2 что-бы скачать JSON используя OkHttp\n" +
                "Нажмите 3 что -бы скачать XML используя UrlConnection\n" +
                "Нажмите 4 что -бы скачать XML используя OkHttp"
    )
    val modeChoice = (readlnOrNull() ?: "0")
    val service = ApplyData()
    when (modeChoice) {
        "1" -> service.getStrFromKiparoUseUrlConnection(JSON_URI)

        "2" -> service.getStrFromKiparoUseOkHttp(JSON_URI)

        "3" -> service.getStrFromKiparoUseUrlConnection(XML_URI)

        "4" -> service.getStrFromKiparoUseOkHttp(XML_URI)
        else -> {
            ""
        }
    }

    // здесь можно что-то делать пока данные скачиваются.

    service.th.join()   // ждем пока данные придут с другого потока

    var listNews: List<News> = emptyList()
    if (modeChoice in ("1".."2")) {
        println("Введите 1-если хотите использовать JsonSimpleParser, 2- eсли Gson")
        listNews = when ((readlnOrNull() ?: "0")) {
            "1" -> JsonSimpleParser().parse(service.strFromKiparo).newsList
            "2" -> GsonParser().parse(service.strFromKiparo).newsList
            else -> {
                emptyList()
            }
        }
    } else if (modeChoice in "3".."4") {

        try {
            listNews = XmlParserJsoup().parse(service.strFromKiparo).newsList
        }catch (e :Exception){
            println("Error Xml Parsing\n${e.printStackTrace()}")
        }

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
        when ((readlnOrNull() ?: "0")) {
            "1" -> listNews.sortedByDescending { it.date }
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





