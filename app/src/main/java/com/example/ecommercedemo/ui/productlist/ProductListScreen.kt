package com.example.ecommercedemo.ui.productlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavController
import com.example.ecommercedemo.ui.model.ProductUi
import com.example.ecommercedemo.ui.model.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ScaffoldWrapper(navController) {
        when (uiState) {
            UiState.Initial -> {
                LoadingState()
            }

            UiState.Loading -> {
                LoadingState()
            }

            UiState.Empty -> {
                EmptyState()
            }

            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                ErrorState(message)
            }

            is UiState.Success<List<ProductUi>> -> {
                val products = (uiState as UiState.Success<List<ProductUi>>).data
                SuccessState(products, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScaffoldWrapper(navController: NavController, child: @Composable () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Products") }) },
        bottomBar = {
            Button(
                onClick = {
                    navController.navigate("settings")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding()
            ) {
                Text("Go to settings")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            child()
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
        Text("No products available", Modifier.align(Alignment.Center))
    }
}

@Composable
private fun SuccessState(products: List<ProductUi>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products.count()) { index ->
            val product = products[index]
            ProductItem(product = product) {
                navController.navigate("productDetail/${product.id}")
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductUi, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "€${product.price}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
