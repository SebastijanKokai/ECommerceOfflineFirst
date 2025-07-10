package com.example.ecommercedemo.ui.model

data class PermissionDialogData(
    val message: String,
    val onConfirm: () -> Unit
)