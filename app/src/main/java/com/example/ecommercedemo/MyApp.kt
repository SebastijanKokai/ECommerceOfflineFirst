package com.example.ecommercedemo

import android.app.Application
import com.example.ecommercedemo.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(productModule)
        }
    }
}