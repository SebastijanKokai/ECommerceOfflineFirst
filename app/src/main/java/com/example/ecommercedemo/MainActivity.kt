package com.example.ecommercedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.ecommercedemo.core.navigation.LocalRootNavController
import com.example.ecommercedemo.core.navigation.graph.RootNavGraph
import com.example.ecommercedemo.ui.settings.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceDemoApp()
        }
    }
}

@Composable
fun EcommerceDemoApp(themeViewModel: ThemeViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val isDarkMode by themeViewModel.isDarkMode.collectAsState()

    CompositionLocalProvider(
        LocalRootNavController provides navController
    ) {
        MaterialTheme(
            colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
        ) {
            RootNavGraph(navController = navController, themeViewModel = themeViewModel)
        }
    }
}
