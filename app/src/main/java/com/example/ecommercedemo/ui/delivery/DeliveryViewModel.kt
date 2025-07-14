package com.example.ecommercedemo.ui.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.core.extension.runCatchingCancellable
import com.example.ecommercedemo.core.scheduler.DeliveryReminderScheduler
import com.example.ecommercedemo.ui.shared.PermissionEvent
import com.example.ecommercedemo.ui.shared.PermissionEvent.ShowNotificationPermissionDialog
import com.example.ecommercedemo.ui.shared.PermissionEvent.ShowSchedulePermissionDialog
import com.example.ecommercedemo.ui.shared.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeliveryViewModel(
    private val scheduler: DeliveryReminderScheduler,
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _permissionEvent = MutableSharedFlow<PermissionEvent>()
    val permissionEvent = _permissionEvent.asSharedFlow()

    private val _scheduleEvent = MutableSharedFlow<UiEvent>()
    val scheduleEvent = _scheduleEvent.asSharedFlow()

    fun schedule(deliveryMillis: Long) {
        viewModelScope.launch {
            if (!scheduler.canScheduleAlarms()) {
                _permissionEvent.emit(ShowSchedulePermissionDialog)
                return@launch
            }

            if (!scheduler.canNotifyUser()) {
                _permissionEvent.emit(ShowNotificationPermissionDialog)
                return@launch
            }

            _isLoading.value = true

            runCatchingCancellable {
                scheduler.scheduleReminder(deliveryMillis)
            }.onSuccess {
                _scheduleEvent.emit(UiEvent.ShowSuccess("Delivery scheduled!"))
            }.onFailure {
                _scheduleEvent.emit(UiEvent.ShowError(it.message ?: "Unknown Error"))
            }

            _isLoading.value = false
        }
    }
}