package com.example.ecommercedemo.core.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.ui.productlist.ProductListScreen
import com.example.ecommercedemo.ui.settings.SettingsScreen
import com.example.ecommercedemo.ui.settings.ThemeViewModel

fun NavGraphBuilder.homeNavGraph(rootNavController: NavController, themeViewModel: ThemeViewModel) {
    composable(AppRoute.ProductList.path) {
        ProductListScreen(rootNavController)
    }
    composable(AppRoute.Profile.path) {
        SettingsScreen(themeViewModel)
    }
}
