package com.example.ecommercedemo.ui.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.LocalRootNavController
import com.example.ecommercedemo.ui.components.QuantityPicker
import com.example.ecommercedemo.ui.model.ProductDetailUi
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
                is UiState.Success<ProductDetailUi?> -> {
                    val product = (uiState as UiState.Success<ProductDetailUi?>).data
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
private fun SuccessState(product: ProductDetailUi?) {
    val navController = LocalRootNavController.current
    var quantity by remember { mutableStateOf(0) }

    product?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = it.image,
                contentDescription = it.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )

            Text(text = it.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Category: ${it.category}", style = MaterialTheme.typography.labelMedium)

            Text(
                text = "Price: €${it.price}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = it.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            QuantityPicker(
                quantity = quantity,
                onIncrease = { quantity++ },
                onDecrease = { if (quantity > 0) quantity-- },
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.navigate(AppRoute.Delivery.path)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add to Cart")
            }
        }
    }
}