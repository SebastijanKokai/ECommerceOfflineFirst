package com.example.ecommercedemo.dispatcher

import com.example.ecommercedemo.core.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatcherProvider(
    override val io: CoroutineDispatcher = StandardTestDispatcher(),
    override val main: CoroutineDispatcher = StandardTestDispatcher(),
    override val default: CoroutineDispatcher = StandardTestDispatcher()
) : CoroutineDispatcherProvider