package com.example.ecommercedemo.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.usecase.ToggleDarkModeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val toggleDarkModeUseCase: ToggleDarkModeUseCase
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    init {
        viewModelScope.launch {
            _isDarkMode.value = toggleDarkModeUseCase.isDarkMode()
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            toggleDarkModeUseCase.execute(Unit)
            _isDarkMode.value = toggleDarkModeUseCase.isDarkMode()
        }
    }
}

