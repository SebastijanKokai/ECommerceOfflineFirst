package com.example.ecommercedemo.ui.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.usecase.ScheduleDeliveryUseCase
import com.example.ecommercedemo.ui.model.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeliveryViewModel(
    private val scheduleDeliveryUseCase: ScheduleDeliveryUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    fun schedule(deliveryMillis: Long) {
        viewModelScope.launch {
            _isLoading.value = true

            runCatching {
                scheduleDeliveryUseCase.execute(deliveryMillis)
            }.onSuccess {
                _event.emit(UiEvent.ShowSuccess("Delivery scheduled!"))
            }.onFailure {
                _event.emit(UiEvent.ShowError(it.message ?: "Unknown Error"))
            }

            _isLoading.value = false
        }
    }

}