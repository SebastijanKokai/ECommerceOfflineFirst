package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.repository.DataStoreRepository

class ToggleDarkModeUseCase(
    private val dataStoreRepository: DataStoreRepository
) : BaseUseCase<Unit, Unit>() {
    companion object {
        private const val DARK_MODE_KEY = "dark_mode"
    }

    override suspend fun execute(params: Unit) {
        val current = dataStoreRepository.getBool(DARK_MODE_KEY) ?: false
        val toggled = !current
        dataStoreRepository.putBool(DARK_MODE_KEY, toggled)
    }

    suspend fun isDarkMode(): Boolean {
        return dataStoreRepository.getBool(DARK_MODE_KEY) ?: false
    }
}
