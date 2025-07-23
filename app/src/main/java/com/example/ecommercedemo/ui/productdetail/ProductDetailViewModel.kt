package com.example.ecommercedemo.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.core.extension.runCatchingCancellable
import com.example.ecommercedemo.domain.usecase.product.GetProductDetailUseCase
import com.example.ecommercedemo.ui.mapper.toProductDetailUiModel
import com.example.ecommercedemo.ui.productdetail.model.ProductDetailUi
import com.example.ecommercedemo.ui.shared.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productId: Int?,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<ProductDetailUi?>>(UiState.Initial)
    val uiState: StateFlow<UiState<ProductDetailUi?>> = _uiState

    fun loadProduct() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            runCatchingCancellable {
                productId?.let {
                    getProductDetailUseCase.execute(it)
                }
            }.map { product ->
                product?.toProductDetailUiModel()
            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "Unknown Error")
            }.onSuccess { productUi ->
                _uiState.value = UiState.Success(productUi)
            }
        }
    }
}
