package com.example.news.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.news.NewsApplication
import com.example.news.data.NewsRepository
import com.example.news.network.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _homeArticles = MutableStateFlow<List<News>>(emptyList())
    val homeArticles: StateFlow<List<News>> = _homeArticles

    private val _searchArticles = MutableStateFlow<List<News>>(emptyList())
    val searchArticles: StateFlow<List<News>> = _searchArticles

    init {
        fetchTopHeadlines("general")
    }

     fun fetchEverything(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _searchArticles.value = repository.fetchEverything(query)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTopHeadlines(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _homeArticles.value = repository.fetchTopHeadlines(category)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsApplication)
                val newsRepository = application.container.newsRepository
                NewsViewModel(newsRepository)
            }
        }
    }

}
