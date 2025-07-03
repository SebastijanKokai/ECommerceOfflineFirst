package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.repository.DeliveryRepository

class ScheduleDeliveryUseCase(private val repository: DeliveryRepository) :
    BaseUseCase<Long, Boolean>() {

    override suspend fun execute(params: Long): Boolean {
        return repository.scheduleDelivery(params)
    }
}