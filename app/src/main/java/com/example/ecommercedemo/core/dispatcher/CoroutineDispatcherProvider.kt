package com.example.ecommercedemo.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}