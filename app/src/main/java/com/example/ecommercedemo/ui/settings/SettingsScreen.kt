package com.example.ecommercedemo.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(themeViewModel: ThemeViewModel) {
    val isDarkMode by themeViewModel.isDarkMode.collectAsState()

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = if (isDarkMode) "Dark Mode is ON" else "Dark Mode is OFF")
        Switch(
            checked = isDarkMode,
            onCheckedChange = { themeViewModel.toggleDarkMode() }
        )
    }

}
