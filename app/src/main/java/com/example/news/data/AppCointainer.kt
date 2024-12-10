package com.example.news.data

import com.example.news.network.NewsApiService
import com.example.news.utils.Constants.API_KEY
import com.kwabenaberko.newsapilib.NewsApiClient

interface AppContainer{
    val newsRepository: NewsRepository
}

class DefaultAppContainer: AppContainer{
    private val newsApiClient = NewsApiClient(API_KEY)
    private val newsApiService = NewsApiService(newsApiClient)
    override val newsRepository: NewsRepository by lazy {
        NetworkNewsRepository(newsApiService)
    }

}