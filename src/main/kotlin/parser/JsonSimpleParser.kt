package parser

import domain.models.News
import domain.models.Root
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.text.SimpleDateFormat
import java.util.*

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
            val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z", Locale.ENGLISH)
            val news = News(
                id = id.toInt(),
                title = title,
                description = description,
                date = formatter.parse(date),
                visible = visible,
                keywords = keyWordsList
            )
            newsList.add(news)
        }
        return Root(name = name, location = location, newsList = newsList)
    }
}