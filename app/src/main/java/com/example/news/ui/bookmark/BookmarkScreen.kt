package com.example.news.ui.bookmark


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.news.data.bookmarkDatabase.BookmarkEntity
import com.example.news.network.News
import com.example.news.ui.NewsList

fun BookmarkEntity.toNews(): News {
    return News(
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage,
        url = this.url
    )
}

fun News.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage,
        url = this.url
    )
}

@Composable
fun BookmarkScreen(viewModel: BookmarkViewModel , navController: NavHostController) {
    val bookmark by viewModel.bookmarks.collectAsState()
    val newsList: List<News> = bookmark.map { it.toNews() }
    val lazyListState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (bookmark.isNotEmpty()) {
            NewsList(newsList,lazyListState,navController)
        } else {
            Text(
                text = "No bookmarks yet.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
