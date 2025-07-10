package com.example.ecommercedemo.ui.shared

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.ecommercedemo.ui.model.UiEvent
import kotlinx.coroutines.flow.Flow

@Composable
fun HandleScheduleEvents(
    scheduleEventFlow: Flow<UiEvent>,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        scheduleEventFlow.collect { event ->
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
}