package com.example.ecommercedemo.di

import com.example.ecommercedemo.ui.settings.ThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val themeModule = module {
    viewModel { ThemeViewModel(get()) }
}