package com.example.ecommercedemo.data.repository

import com.example.ecommercedemo.core.scheduler.DeliveryReminderScheduler
import com.example.ecommercedemo.domain.repository.DeliveryRepository
import kotlinx.coroutines.delay

class DeliveryRepositoryImpl(private val scheduler: DeliveryReminderScheduler) :
    DeliveryRepository {
    override suspend fun scheduleDelivery(deliveryTimeMillis: Long): Boolean {
        delay(1500) // Fake API call
        scheduler.scheduleReminder(deliveryTimeMillis)
        return true
    }
}