package com.example.ecommercedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommercedemo.ui.productdetail.ProductDetailScreen
import com.example.ecommercedemo.ui.productlist.ProductListScreen

@Composable
fun ShopNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            ProductListScreen(navController = navController)
        }
        composable("productDetail/{productId}") { navBackStackEntry ->
            val productId = navBackStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(productId = productId)
        }
    }

}