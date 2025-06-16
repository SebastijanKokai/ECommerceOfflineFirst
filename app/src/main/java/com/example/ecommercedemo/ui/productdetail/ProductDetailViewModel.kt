package com.example.ecommercedemo.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productId: String,
    private val repository: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<ProductDomain?>(null)
    val product: StateFlow<ProductDomain?> = _product

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _product.value = ProductDomain(productId, "Product $productId", 99.99)
        }
    }
}
