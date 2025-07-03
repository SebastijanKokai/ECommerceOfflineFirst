package com.example.ecommercedemo.ui.productdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.LocalRootNavController
import com.example.ecommercedemo.ui.model.ProductUi
import com.example.ecommercedemo.ui.model.UiState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int?,
    viewModel: ProductDetailViewModel = koinViewModel(parameters = {
        parametersOf(
            productId
        )
    })
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Product Detail") }) }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            when (uiState) {
                UiState.Empty -> EmptyState()
                is UiState.Error -> {
                    val message = (uiState as UiState.Error).message
                    ErrorState(message)
                }

                UiState.Initial -> LoadingState()
                UiState.Loading -> LoadingState()
                is UiState.Success<ProductUi?> -> {
                    val product = (uiState as UiState.Success<ProductUi?>).data
                    SuccessState(product)
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ErrorState(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Error: $message", Modifier.align(Alignment.Center))
    }
}

@Composable
private fun EmptyState() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("No product found", Modifier.align(Alignment.Center))
    }
}

@Composable
private fun SuccessState(product: ProductUi?) {
    val navController = LocalRootNavController.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = "Product ID: ${product?.id}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Product Name: ${product?.name}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Price: $${product?.price}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            navController.navigate(AppRoute.Delivery.path)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Add to cart")
        }
    }
}
