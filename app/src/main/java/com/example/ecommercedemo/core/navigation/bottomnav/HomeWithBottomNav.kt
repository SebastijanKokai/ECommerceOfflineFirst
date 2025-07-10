package com.example.ecommercedemo.core.navigation.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.graph.homeNavGraph
import com.example.ecommercedemo.ui.settings.ThemeViewModel
import com.example.ecommercedemo.ui.shared.components.CartButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeWithBottomNav(
    themeViewModel: ThemeViewModel
) {
    val bottomNavController = rememberNavController()
    val items = listOf(BottomNavItem.Home, BottomNavItem.Profile)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    CartButton()
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = bottomNavController, items = items)
        },
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