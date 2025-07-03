package com.example.ecommercedemo.di

import com.example.ecommercedemo.core.scheduler.DeliveryReminderScheduler
import com.example.ecommercedemo.data.repository.DeliveryRepositoryImpl
import com.example.ecommercedemo.domain.repository.DeliveryRepository
import com.example.ecommercedemo.domain.usecase.ScheduleDeliveryUseCase
import com.example.ecommercedemo.ui.delivery.DeliveryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val deliverySchedulerModule = module {
    single<DeliveryReminderScheduler> { DeliveryReminderScheduler(context = androidContext()) }
    single<DeliveryRepository> { DeliveryRepositoryImpl(get()) }
    single<ScheduleDeliveryUseCase> { ScheduleDeliveryUseCase(get()) }
    viewModel {
        DeliveryViewModel(get())
    }
}