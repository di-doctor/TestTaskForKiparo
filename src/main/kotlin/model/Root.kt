package model

data class Root(
    val name: String = "default name",
    val location: String = "default location",
    val newsList: List<News> = emptyList(),
)
