import model.News
import model.Root
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

/*val id: Int,
val title: String,
val description: String,
val date: String,
val visible: Boolean,
val keywords: List<String>,*/

private const val NAME = "name"
private const val LOCATION = "location"
private const val NEWS_LIST = "news"

private const val ID = "id"
private const val TITLE = "title"
private const val DESCRIPTION = "description"
private const val DATE = "date"
private const val VISIBLE = "visible"
private const val KEYWORDS = "keywords"


class JsonSimpleParser {
    fun parse(str: String): Root {
        val parser = JSONParser()
        val rootJsonObject = parser.parse(str) as JSONObject
        val name = rootJsonObject[NAME] as String? ?: ""
        val location = rootJsonObject[LOCATION] as String? ?: ""
        val newsJsonList = rootJsonObject[NEWS_LIST] as JSONArray

        val newsList: MutableList<News> = mutableListOf()
        for (item in newsJsonList) {
            val itemJsonObject = item as JSONObject

            val id = itemJsonObject[ID] as Long? ?: 0L
            val title = itemJsonObject[TITLE] as String? ?: ""
            val description = itemJsonObject[DESCRIPTION] as String? ?: ""
            val date = itemJsonObject[DATE] as String? ?: ""
            val visible = itemJsonObject[VISIBLE] as Boolean? ?: false

            val keyWordsList: MutableList<String> = mutableListOf()
            val keywordsJsonList = itemJsonObject[KEYWORDS] as JSONArray
            for (itemKeywords in keywordsJsonList) {
                keyWordsList.add(itemKeywords as String)
            }

            val news = News(
                id = id.toInt(),
                title = title,
                description = description,
                date = date,
                visible = visible,
                keywords = keyWordsList
            )
            newsList.add(news)
        }
        return Root(name = name, location = location, newsList = newsList)
    }
}