package com.example.ecommercedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ecommercedemo.navigation.ShopNavGraph
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
    val isDarkMode by themeViewModel.isDarkMode.collectAsState()

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShopNavGraph(themeViewModel = themeViewModel)
        }
    }
}
