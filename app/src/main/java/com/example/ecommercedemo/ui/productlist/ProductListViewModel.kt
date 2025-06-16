package com.example.ecommercedemo.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<ProductDomain>>(emptyList())
    val products: StateFlow<List<ProductDomain>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _products.value = getProductListUseCase.execute(Unit)
        }
    }
}