package com.example.ecommercedemo.ui.model

sealed class UiEvent {
    data class ShowSuccess(val message: String) : UiEvent()
    data class ShowError(val message: String) : UiEvent()
}