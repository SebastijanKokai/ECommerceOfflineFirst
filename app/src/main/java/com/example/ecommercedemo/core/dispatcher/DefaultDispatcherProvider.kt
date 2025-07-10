package com.example.ecommercedemo.core.dispatcher

import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider : CoroutineDispatcherProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}
