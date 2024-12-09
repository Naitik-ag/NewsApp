package com.example.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.news.NewsScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.R
import com.example.news.data.News
import com.example.news.ui.BookmarkScreen
import com.example.news.ui.home.HomeScreen
import com.example.news.ui.SearchScreen
import com.example.news.ui.home.HomeViewModel

val newsList = listOf(
    News("Breaking News", "This is a detailed description of the first news item.", R.drawable.images),
    News("Tech Update", "New advancements in technology are being introduced.", R.drawable.images),
    News("Sports Highlights", "Catch up on all the latest sports action here.", R.drawable.images)
)

@Composable
fun NewsNavHost(
    navController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = NewsScreen.Home.name,
        modifier = modifier
    ){
        composable(route = NewsScreen.Home.name){
            HomeScreen(newsList)
        }
        composable(route = NewsScreen.Search.name){
            SearchScreen(newsList)
        }
        composable(route = NewsScreen.Bookmark.name){
            BookmarkScreen(newsList)
        }
    }
}