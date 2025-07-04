package com.example.ecommercedemo.di

import com.example.ecommercedemo.core.scheduler.DeliveryReminderScheduler
import com.example.ecommercedemo.ui.delivery.DeliveryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val deliverySchedulerModule = module {
    single<DeliveryReminderScheduler> { DeliveryReminderScheduler(context = androidContext()) }
    viewModel {
        DeliveryViewModel(get())
    }
}