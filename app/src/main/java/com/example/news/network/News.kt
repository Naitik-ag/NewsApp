package com.example.news.network

data class News(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String
)

fun com.kwabenaberko.newsapilib.models.Article.toDomainModel(): com.example.news.network.News {
    return com.example.news.network.News(
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt
    )
}