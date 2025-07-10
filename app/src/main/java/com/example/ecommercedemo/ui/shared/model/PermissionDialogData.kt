package com.example.ecommercedemo.ui.shared.model

data class PermissionDialogData(
    val message: String,
    val onConfirm: () -> Unit
)