package com.example.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.news.NewsScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.ui.bookmark.BookmarkScreen
import com.example.news.ui.NewsViewModel
import com.example.news.ui.home.HomeScreen
import com.example.news.ui.search.SearchScreen
import com.example.news.ui.bookmark.BookmarkViewModel


@Composable
fun NewsNavHost(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    bookmarkViewModel: BookmarkViewModel,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = NewsScreen.Home.name,
        modifier = modifier
    ){
        composable(route = NewsScreen.Home.name){
            HomeScreen(newsViewModel)
        }
        composable(route = NewsScreen.Search.name){
            SearchScreen(newsViewModel)
        }
        composable(route = NewsScreen.Bookmark.name){
            BookmarkScreen(bookmarkViewModel)
        }
    }
}