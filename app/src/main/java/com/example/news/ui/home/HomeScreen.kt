package com.example.news.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.news.R
import com.example.news.data.News
import com.example.news.ui.NewsList
import com.example.news.ui.theme.NewsTheme

@Composable
fun HomeScreen(newsList: List<News>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Latest News",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        NewsList(newsList = newsList)
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NewsTheme {
        val newsList = listOf(
            News("Breaking News", "This is a detailed description of the first news item.", R.drawable.images),
            News("Tech Update", "New advancements in technology are being introduced.", R.drawable.images),
            News("Sports Highlights", "Catch up on all the latest sports action here.", R.drawable.images)
        )
        HomeScreen(newsList = newsList)
    }

}

