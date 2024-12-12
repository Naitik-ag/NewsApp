package com.example.news.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.news.R
import com.example.news.network.News
import com.example.news.ui.NewsList
import com.example.news.ui.NewsViewModel
import com.example.news.ui.bookmark.BookmarkViewModel
import com.example.news.ui.theme.NewsTheme
import com.example.news.utils.Constants

@Composable
fun HomeScreen(viewModel: NewsViewModel) {
    val news by viewModel.homeArticles.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("All") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Latest News",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                Text(
                    text = selectedCategory,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(8.dp)
                        .padding(bottom = 16.dp),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    Constants.CATEGORIES.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(
                                text = category,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            ) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                                val categoryToFetch = if (category == "All") "general" else category
                                viewModel.fetchTopHeadlines(categoryToFetch)
                            }
                        )
                    }
                }
            }
        }

        if (news.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            NewsList(news)
        }
    }
}



