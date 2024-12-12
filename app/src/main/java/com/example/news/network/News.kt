package com.example.news.network

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class News(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?
)

fun com.kwabenaberko.newsapilib.models.Article.toDomainModel(): com.example.news.network.News {
    return com.example.news.network.News(
        title = this.title?: "No Title",
        description = this.description?: "No Description",
        url = this.url?: "",
        urlToImage = this.urlToImage?:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUrgu4a7W_OM8LmAuN7Prk8dzWXm7PVB_FmA&s"
    )
}