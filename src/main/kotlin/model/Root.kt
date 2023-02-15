package model

import com.google.gson.annotations.SerializedName

data class Root(
    val name: String = "default name",
    val location: String = "default location",
    @SerializedName("news")
    val newsList: List<News> = emptyList(),
)
