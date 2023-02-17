package model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.google.gson.annotations.SerializedName


data class Root(
    val name: String = "default name",
    val location: String = "default location",

    @JsonProperty ("news")
    @SerializedName("news")
    val newsList: List<News> = emptyList(),
)

