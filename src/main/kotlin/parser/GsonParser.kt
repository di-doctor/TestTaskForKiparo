package parser

import com.google.gson.GsonBuilder
import domain.models.Root

class GsonParser {
    fun parse(str:String): Root {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss Z").create()
        return gson.fromJson(str, Root::class.java)
    }
}