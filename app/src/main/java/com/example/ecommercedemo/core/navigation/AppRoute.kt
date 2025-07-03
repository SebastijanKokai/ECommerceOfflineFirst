package com.example.ecommercedemo.core.navigation

sealed class AppRoute(val path: String, val name: String) {
    object Home : AppRoute("home", "Home")
    object Profile : AppRoute("profile", "Profile")
    object ProductList : AppRoute("productList", "Product List")
    object ProductDetail : AppRoute("productDetail/{productId}", "Product Detail")
    object Delivery : AppRoute("delivery", "Delivery")
    object Cart : AppRoute("cart", "Cart")
    object Settings : AppRoute("settings", "Settings")
    object Checkout : AppRoute("checkout", "Checkout")
}
