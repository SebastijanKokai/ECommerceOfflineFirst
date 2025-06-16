package com.example.ecommercedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ecommercedemo.navigation.ShopNavGraph
import com.example.ecommercedemo.ui.theme.EcommerceDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceDemoTheme {
                ShopNavGraph()
            }
        }
    }
}