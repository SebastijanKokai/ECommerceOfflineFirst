package com.example.ecommercedemo.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.usecase.GetProductListUseCase
import com.example.ecommercedemo.ui.mapper.toUiModel
import com.example.ecommercedemo.ui.model.ProductUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<ProductUi>>(emptyList())
    val products: StateFlow<List<ProductUi>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val products = getProductListUseCase.execute(Unit)
            _products.value = products.toUiModel()
        }
    }
}