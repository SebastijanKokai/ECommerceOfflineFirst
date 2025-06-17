package com.example.ecommercedemo.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.usecase.GetProductDetailUseCase
import com.example.ecommercedemo.ui.mapper.toUiModel
import com.example.ecommercedemo.ui.model.ProductUi
import com.example.ecommercedemo.ui.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productId: String,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<ProductUi?>>(UiState.Initial)
    val uiState: StateFlow<UiState<ProductUi?>> = _uiState

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            runCatching {
                getProductDetailUseCase.execute(productId)?.toUiModel()
            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "Unknown Error")
            }.onSuccess { productUi ->
                _uiState.value = UiState.Success(productUi)
            }
        }
    }
}
