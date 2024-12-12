package com.example.news.network

import android.util.Log
import com.example.news.utils.Constants.API_KEY
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class NewsApiService(private val newsApiClient: NewsApiClient) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getTopHeadlines(category: String): List<News>? = suspendCancellableCoroutine { continuation ->
        val request = TopHeadlinesRequest.Builder()
            .category(category)
            .language("en")
            .build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                val news = response?.articles?.map { it.toDomainModel() }
                continuation.resume(news?: emptyList()) {}
            }

            override fun onFailure(throwable: Throwable) {
                continuation.resumeWithException(throwable)
            }
        })
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getEverything(query: String): List<com.example.news.network.News>? =
        suspendCancellableCoroutine { continuation ->
            val request = EverythingRequest.Builder()
                .q(query)
                .build()

            newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    val news = response.articles?.map {
                        try { it.toDomainModel() }
                        catch (e: Exception) {
                            Log.e("Article Mapping", "Error mapping article: ${e.message}", e)
                            null
                        }
                    }?.filterNotNull()
                        ?: emptyList()
                    continuation.resume(news?: emptyList()) {}
                }

                override fun onFailure(throwable: Throwable) {
                    continuation.resumeWithException(throwable)
                }
            })
    }
}