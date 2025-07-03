package com.example.ecommercedemo.ui.delivery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommercedemo.core.extension.formatTime
import com.example.ecommercedemo.ui.model.UiEvent
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryScreen(viewModel: DeliveryViewModel = koinViewModel()) {
    val isLoading = viewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is UiEvent.ShowSuccess -> {
                    snackbarHostState.showSnackbar(event.message)
                }

                is UiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Schedule Delivery") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            val deliveryTime = remember { LocalDateTime.now().plusHours(2) }
            val deliveryMillis = remember {
                deliveryTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            }
            val formattedTime = deliveryTime.formatTime("HH:mm")

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Delivery at: $formattedTime")
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = { viewModel.schedule(deliveryMillis) },
                    enabled = !isLoading.value
                ) {
                    if (isLoading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text("Set Delivery Reminder")
                }
            }

        }
    }
}