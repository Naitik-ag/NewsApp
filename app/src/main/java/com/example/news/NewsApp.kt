package com.example.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.news.data.News
import com.example.news.ui.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(modifier: Modifier = Modifier){
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = { NewsAppBar(scrollBehaviour = scrollBehaviour) }
    ) {contentPadding ->
    Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ){
            HomeScreen(
                listOf(
                    News("Breaking News", "This is a detailed description of the first news item.", R.drawable.images),
                    News("Tech Update", "New advancements in technology are being introduced.", R.drawable.images),
                    News("Sports Highlights", "Catch up on all the latest sports action here.", R.drawable.images)
                )
            )
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(scrollBehaviour: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehaviour,
        title = { Text(text = "News" , style = MaterialTheme.typography.displayMedium) },
        modifier = modifier
    )
}
