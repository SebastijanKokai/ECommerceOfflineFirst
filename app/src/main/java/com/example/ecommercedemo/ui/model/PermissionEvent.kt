package com.example.ecommercedemo.ui.model

sealed class PermissionEvent {
    object ShowSchedulePermissionDialog : PermissionEvent()
    object ShowNotificationPermissionDialog : PermissionEvent()
}