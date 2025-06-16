package com.example.ecommercedemo.ui.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    viewModel: ProductDetailViewModel = koinViewModel(parameters = {
        parametersOf(
            productId
        )
    })
) {
    val product by viewModel.product.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Product Detail") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(text = "Product ID: ${product?.id}")
            Text(text = "Product Name: ${product?.name}")
            Text(text = "Price: $${product?.price}")
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("Add to cart")
            }
        }
    }
}