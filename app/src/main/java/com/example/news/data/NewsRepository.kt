package com.example.news.data

import com.example.news.network.NewsApiService

interface NewsRepository{
    suspend fun fetchEverything(query: String): List<com.example.news.network.News>
    suspend fun fetchTopHeadlines(): List<com.example.news.network.News>
}

class NetworkNewsRepository(private val newsApiService: NewsApiService): NewsRepository{
    override suspend fun fetchEverything(query: String): List<com.example.news.network.News> {
        return newsApiService.getEverything(query) ?: emptyList()
    }

    override suspend fun fetchTopHeadlines(): List<com.example.news.network.News> {
        return newsApiService.getTopHeadlines() ?: emptyList()
    }
}