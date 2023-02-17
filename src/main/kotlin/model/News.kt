package model

import java.util.Date

data class News(
    val id: Int,
    val title: String,
    val description: String,
    val date: Date?,
    val visible: Boolean,
    val keywords: List<String>,
)