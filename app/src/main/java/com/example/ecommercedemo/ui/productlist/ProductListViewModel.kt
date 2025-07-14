package com.example.ecommercedemo.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.core.extension.runCatchingCancellable
import com.example.ecommercedemo.domain.usecase.product.GetProductListUseCase
import com.example.ecommercedemo.domain.usecase.product.RefreshProductsUseCase
import com.example.ecommercedemo.ui.mapper.toProductListUiModel
import com.example.ecommercedemo.ui.productlist.model.ProductListUi
import com.example.ecommercedemo.ui.shared.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val refreshProductsUseCase: RefreshProductsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<ProductListUi>>>(UiState.Initial)
    val uiState: StateFlow<UiState<List<ProductListUi>>> = _uiState

    init {
        loadProducts()
        refreshProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            getProductListUseCase.execute(Unit)
                .map { products -> products.toProductListUiModel() }
                .map { uiList -> if (uiList.isEmpty()) UiState.Empty else UiState.Success(uiList) }
                .onStart { _uiState.value = UiState.Loading }
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "Unknown Error")
                }
                .collect { uiState ->
                    _uiState.value = uiState
                }
        }
    }

    fun refreshProducts() {
        viewModelScope.launch {
            runCatchingCancellable {
                refreshProductsUseCase.execute(Unit)
            }.onFailure { e ->
                // @TODO Implement Error Logging
            }
        }
    }
}