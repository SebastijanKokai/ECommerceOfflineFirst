package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.repository.DataStoreRepository

class ToggleDarkModeUseCase(
    private val dataStoreRepository: DataStoreRepository
) : BaseUseCase<Unit, Unit>() {
    companion object {
        private const val DARK_MODE_KEY = "dark_mode"
    }

    override suspend fun execute(params: Unit) {
        val current = dataStoreRepository.getInt(DARK_MODE_KEY) ?: 0
        val toggled = if (current == 0) 1 else 0
        dataStoreRepository.putInt(DARK_MODE_KEY, toggled)
    }

    suspend fun isDarkMode(): Boolean {
        return (dataStoreRepository.getInt(DARK_MODE_KEY) ?: 0) == 1
    }
}
