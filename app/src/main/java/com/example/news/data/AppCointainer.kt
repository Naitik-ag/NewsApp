package com.example.news.data

import com.example.news.network.NewsApiService
import com.example.news.utils.Constants.API_KEY
import com.kwabenaberko.newsapilib.NewsApiClient
import android.content.Context
import com.example.news.data.bookmarkDatabase.BookmarkDatabase
import com.example.news.data.bookmarkDatabase.BookmarkRepository
import com.example.news.data.bookmarkDatabase.OfflineBookmarkRepository

interface AppContainer{
    val newsRepository: NewsRepository
    val bookmarkRepository: BookmarkRepository
}

class DefaultAppContainer(private val context: Context): AppContainer{

    private val newsApiClient = NewsApiClient(API_KEY)
    private val newsApiService = NewsApiService(newsApiClient)
    override val newsRepository: NewsRepository by lazy {
        NetworkNewsRepository(newsApiService)
    }

    override val bookmarkRepository: BookmarkRepository by lazy {
        OfflineBookmarkRepository(BookmarkDatabase.getDatabase(context).bookmarkDao())
    }
}