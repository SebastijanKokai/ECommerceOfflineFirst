package com.example.ecommercedemo.di

import com.example.ecommercedemo.core.dispatcher.CoroutineDispatcherProvider
import com.example.ecommercedemo.core.dispatcher.DefaultDispatcherProvider
import org.koin.dsl.module

val appModule = module {
    single<CoroutineDispatcherProvider> { DefaultDispatcherProvider() }
}