package com.example.ecommercedemo.di

import com.example.ecommercedemo.data.repository.DataStoreRepositoryImpl
import com.example.ecommercedemo.domain.repository.DataStoreRepository
import com.example.ecommercedemo.domain.usecase.ToggleDarkModeUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStoreRepository> { DataStoreRepositoryImpl(androidContext()) }
    single { ToggleDarkModeUseCase(get()) }
}