package model

data  class News(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val visible: Boolean,
    val keywords: List<String>,
)