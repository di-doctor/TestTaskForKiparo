package parser

import InternetService.ApplyData
import model.News
import model.Root
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.*

class XmlParserJsoup {
    fun parse(str:String) :Root{
        val doc = Jsoup.parse(str)
        val location = doc.body().getElementsByTag("location").text()
        val name = doc.body().getElementsByTag("name").text()

        val newsElement = doc.body().getElementsByTag("news")
        //val listElement = newsElement.first()?.getElementsByTag("element")
        val listElement = newsElement.first()?.select("news > element")
        val newsList = mutableListOf<News>()
        if (listElement != null) {
            for (element in listElement) {

                val dateStr = element.getElementsByTag("date").firstOrNull()?.text() ?: ""
                val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z", Locale.ENGLISH)
                val date: Date? = formatter.parse(dateStr)

                val description = element.getElementsByTag("description").first()?.text() ?: ""
                val id = element.getElementsByTag("id").first()?.text()?.toIntOrNull() ?: -1
                val title = element.getElementsByTag("title").first()?.text() ?: ""
                val visible = (element.getElementsByTag("visible").first()?.text() ?: "").toBoolean()

                val keywordsElementList = element.getElementsByTag("keywords")
                    .first()?.getElementsByTag("element") ?: emptyList<Element>()
                val keyList = mutableListOf<String>()
                keywordsElementList.map { it.text() }.let { keyList.addAll(it) }

                News(id, title, description, date, visible, keyList).also { news: News -> newsList.add(news) }
            }
        }
        return Root(name, location, newsList).also { println(it) }
    }
}