package com.example.ecommercedemo.core.navigation.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.graph.homeNavGraph
import com.example.ecommercedemo.ui.settings.ThemeViewModel

@Composable
fun HomeWithBottomNav(
    themeViewModel: ThemeViewModel
) {
    val bottomNavController = rememberNavController()
    val items = listOf(BottomNavItem.Home, BottomNavItem.Profile)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = bottomNavController, items = items)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = AppRoute.ProductList.path,
            modifier = Modifier.padding(innerPadding)
        ) {
            homeNavGraph(themeViewModel)
        }
    }
}
