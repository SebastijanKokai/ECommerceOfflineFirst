package com.example.ecommercedemo.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.ecommercedemo.core.extension.openAppNotificationSettings
import com.example.ecommercedemo.core.extension.requestScheduleExactAlarmPermission
import com.example.ecommercedemo.ui.shared.model.PermissionDialogData
import kotlinx.coroutines.flow.Flow

@Composable
fun HandlePermissionEvents(
    permissionEventFlow: Flow<PermissionEvent>,
    onDialogRequested: (PermissionDialogData) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        permissionEventFlow.collect { event ->
            when (event) {
                PermissionEvent.ShowSchedulePermissionDialog -> {
                    onDialogRequested(
                        PermissionDialogData(
                            message = "This permission allows the app to schedule exact delivery reminders, even when idle.",
                            onConfirm = {
                                context.requestScheduleExactAlarmPermission()
                            }
                        )
                    )
                }

                PermissionEvent.ShowNotificationPermissionDialog -> {
                    onDialogRequested(
                        PermissionDialogData(
                            message = "This permission is required to notify you when your delivery is on its way.",
                            onConfirm = {
                                context.openAppNotificationSettings()
                            }
                        )
                    )
                }
            }
        }
    }
}