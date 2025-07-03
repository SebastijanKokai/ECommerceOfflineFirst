package com.example.ecommercedemo.core.navigation.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ecommercedemo.core.navigation.AppRoute

sealed class BottomNavItem(
    val route: AppRoute,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = AppRoute.ProductList,
        icon = Icons.Filled.Home
    )

    object Profile : BottomNavItem(
        route = AppRoute.Profile,
        icon = Icons.Filled.Person
    )
}