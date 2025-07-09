package com.example.ecommercedemo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.LocalRootNavController

@Composable
fun CartButton() {
    val rootNavController = LocalRootNavController.current

    IconButton(onClick = {
        rootNavController.navigate(AppRoute.Cart.path)
    }) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart"
        )
    }
}