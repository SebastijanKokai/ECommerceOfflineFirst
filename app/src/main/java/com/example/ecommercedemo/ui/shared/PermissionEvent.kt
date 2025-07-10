package com.example.ecommercedemo.ui.shared

sealed class PermissionEvent {
    object ShowSchedulePermissionDialog : PermissionEvent()
    object ShowNotificationPermissionDialog : PermissionEvent()
}