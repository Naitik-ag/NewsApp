package com.example.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.news.NewsScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.R
import com.example.news.network.News
import com.example.news.ui.BookmarkScreen
import com.example.news.ui.NewsViewModel
import com.example.news.ui.home.HomeScreen
import com.example.news.ui.SearchScreen
import com.example.news.ui.home.HomeViewModel



@Composable
fun NewsNavHost(
    navController: NavHostController,
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = NewsScreen.Home.name,
        modifier = modifier
    ){
        composable(route = NewsScreen.Home.name){
            HomeScreen(viewModel)
        }
        composable(route = NewsScreen.Search.name){
            SearchScreen(viewModel)
        }
        composable(route = NewsScreen.Bookmark.name){
            BookmarkScreen(viewModel)
        }
    }
}