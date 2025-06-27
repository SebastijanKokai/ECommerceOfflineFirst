package com.example.ecommercedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecommercedemo.ui.productdetail.ProductDetailScreen
import com.example.ecommercedemo.ui.productlist.ProductListScreen
import com.example.ecommercedemo.ui.settings.SettingsScreen
import com.example.ecommercedemo.ui.settings.ThemeViewModel

@Composable
fun ShopNavGraph(
    navController: NavHostController = rememberNavController(),
    themeViewModel: ThemeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            ProductListScreen(navController = navController)
        }
        composable(
            "productDetail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val productId = navBackStackEntry.arguments?.getInt("productId") ?: -1
            ProductDetailScreen(productId = productId)
        }
        composable("settings") {
            SettingsScreen(themeViewModel = themeViewModel)
        }
    }

}