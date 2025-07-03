package com.example.ecommercedemo.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalRootNavController = staticCompositionLocalOf<NavHostController> {
    error("Root NavController not provided")
}