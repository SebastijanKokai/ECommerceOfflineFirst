package com.example.ecommercedemo.core.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.bottomnav.HomeWithBottomNav
import com.example.ecommercedemo.ui.cart.CartScreen
import com.example.ecommercedemo.ui.cart.CheckoutScreen
import com.example.ecommercedemo.ui.delivery.DeliveryScreen
import com.example.ecommercedemo.ui.productdetail.ProductDetailScreen
import com.example.ecommercedemo.ui.settings.ThemeViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    themeViewModel: ThemeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Home.path,
    ) {
        composable(AppRoute.Home.path) {
            HomeWithBottomNav(rootNavController = navController, themeViewModel)
        }
        composable(
            AppRoute.ProductDetail.path,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val productId =
                navBackStackEntry.arguments?.getInt(AppRoute.ProductDetail.ARG_PRODUCT_ID) ?: -1
            ProductDetailScreen(productId = productId)
        }
        composable(AppRoute.Cart.path) {
            CartScreen()
        }
        composable(AppRoute.Checkout.path) {
            CheckoutScreen()
        }
        composable(AppRoute.Delivery.path) {
            DeliveryScreen()
        }
    }

}