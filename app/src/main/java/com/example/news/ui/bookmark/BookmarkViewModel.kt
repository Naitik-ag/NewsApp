package com.example.news.ui.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.news.NewsApplication
import com.example.news.data.bookmarkDatabase.BookmarkEntity
import com.example.news.data.bookmarkDatabase.BookmarkRepository
import com.example.news.network.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookmarkViewModel(private val bookmarkRepository: BookmarkRepository) : ViewModel() {

    private val _bookmarks = MutableStateFlow<List<BookmarkEntity>>(emptyList())
    val bookmarks: StateFlow<List<BookmarkEntity>> = _bookmarks

    fun isBookmarked(news: News): Boolean {
        return _bookmarks.value.any { it.title == news.title }
    }

    init {
        loadBookmarks()
    }

    private fun loadBookmarks() {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.getAllBookmarks()
                .catch { throwable ->
                    _bookmarks.value = emptyList()
                    Log.e("BookmarkViewModel", "Error loading bookmarks", throwable)
                }
                .collect { bookmarkList ->
                    withContext(Dispatchers.Main) {
                        _bookmarks.value = bookmarkList
                    }
                }
        }
    }

    fun addBookmark(bookmarkEntity: BookmarkEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.addBookmark(bookmarkEntity)
            withContext(Dispatchers.Main) {
                _bookmarks.value += bookmarkEntity
            }

        }
    }

    fun removeBookmark(bookmarkTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.deleteBookmarkById(bookmarkTitle)
            withContext(Dispatchers.Main) {
                _bookmarks.value = _bookmarks.value.filterNot { it.title == bookmarkTitle }
            }

        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsApplication)
                val bookmarkRepository = application.container.bookmarkRepository
                BookmarkViewModel(bookmarkRepository)
            }
        }
    }
}
