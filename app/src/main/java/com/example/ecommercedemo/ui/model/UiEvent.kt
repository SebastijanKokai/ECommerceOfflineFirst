package com.example.ecommercedemo.ui.model

sealed class UiEvent {
    class ShowSuccess(val message: String) : UiEvent()
    class ShowError(val message: String) : UiEvent()
}