package com.example.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.news.navigation.NewsBottomNavBar
import com.example.news.navigation.NewsNavHost
import com.example.news.ui.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.news.ui.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(
    newsViewModel: NewsViewModel = viewModel(factory = NewsViewModel.Factory),
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeUiState by homeViewModel.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = { NewsAppBar(scrollBehaviour = scrollBehaviour) },
        bottomBar = {NewsBottomNavBar(
            homeViewModel,
            navController,
            homeUiState
        )}

    ) {contentPadding ->
    Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ){
            NewsNavHost(
                navController,
                newsViewModel
            )
        }
    }

}

enum class NewsScreen(){
    Home,
    Search,
    Bookmark
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

