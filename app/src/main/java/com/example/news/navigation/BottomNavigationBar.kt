package com.example.news.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.news.NewsScreen
import com.example.news.ui.home.HomeViewModel
import com.example.news.ui.home.NewsUiState


data class BottomNavItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
@Composable
fun NewsBottomNavBar(
    viewModel: HomeViewModel,
    navController: NavHostController,
    homeUiState: NewsUiState
) {
    val items = listOf(
        BottomNavItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavItems(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            hasNews = false
        ),
        BottomNavItems(
            title = "Bookmark",
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.Bookmarks,
            hasNews = false
        )
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = homeUiState.selectedScreenIndex == index,
                onClick = {
                    viewModel.updateSelectedScreenIndex(index)
                    navController.navigate(item.title)
                },
                label = {
                    Text(item.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            when {
                                item.badgeCount != null -> {
                                    Badge { Text(text = item.badgeCount.toString()) }
                                }
                                item.hasNews -> {
                                    Badge()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == homeUiState.selectedScreenIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

