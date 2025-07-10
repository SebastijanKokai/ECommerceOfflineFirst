package com.example.ecommercedemo.ui.delivery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ecommercedemo.core.extension.formatTime
import com.example.ecommercedemo.core.extension.openAppNotificationSettings
import com.example.ecommercedemo.core.extension.requestScheduleExactAlarmPermission
import com.example.ecommercedemo.ui.model.PermissionEvent
import com.example.ecommercedemo.ui.model.UiEvent
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryScreen(viewModel: DeliveryViewModel = koinViewModel()) {
    val context = LocalContext.current
    val isLoading = viewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.permissionEvent.collect { event ->
            when (event) {
                PermissionEvent.ShowNotificationPermissionDialog -> {
                    context.openAppNotificationSettings()
                }

                PermissionEvent.ShowSchedulePermissionDialog -> {
                    context.requestScheduleExactAlarmPermission()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.scheduleEvent.collect { event ->
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
        DeliveryContent(
            modifier = Modifier.padding(padding),
            isLoading = isLoading.value,
            onScheduleClick = { millis ->
                viewModel.schedule(millis)
            })
    }
}

@Composable
private fun DeliveryContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onScheduleClick: (Long) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        val deliveryTime = remember { LocalDateTime.now().plusHours(2) }
        val deliveryMillis = remember {
            deliveryTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }
        val formattedTime = deliveryTime.formatTime("HH:mm")

        DeliveryCard(
            time = formattedTime,
            isLoading = isLoading,
            onClick = { onScheduleClick(deliveryMillis) }
        )
    }
}

@Composable
private fun DeliveryCard(
    time: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Your delivery will arrive at:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = time,
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(Modifier.height(24.dp))
            DeliveryButton(
                isLoading = isLoading,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun DeliveryButton(
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text("Remind Me Before Delivery")
    }
}

