package com.example.ecommercedemo.domain.repository

interface DeliveryRepository {
    suspend fun scheduleDelivery(deliveryTimeMillis: Long): Boolean
}