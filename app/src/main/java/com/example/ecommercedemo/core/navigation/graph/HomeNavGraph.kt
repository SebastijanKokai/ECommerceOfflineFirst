package com.example.ecommercedemo.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.ui.productlist.ProductListScreen
import com.example.ecommercedemo.ui.settings.SettingsScreen
import com.example.ecommercedemo.ui.settings.ThemeViewModel

fun NavGraphBuilder.homeNavGraph(themeViewModel: ThemeViewModel) {
    composable(AppRoute.ProductList.path) {
        ProductListScreen()
    }
    composable(AppRoute.Profile.path) {
        SettingsScreen(themeViewModel)
    }
}
