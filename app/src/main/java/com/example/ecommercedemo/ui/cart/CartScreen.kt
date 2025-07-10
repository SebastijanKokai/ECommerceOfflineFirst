package com.example.ecommercedemo.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.LocalRootNavController
import com.example.ecommercedemo.ui.model.CartProductUi
import com.example.ecommercedemo.ui.model.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(viewModel: CartViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.Initial -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            val message = (uiState as UiState.Error).message
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $message")
            }
        }

        is UiState.Empty -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Your cart is empty.")
            }
        }

        is UiState.Success -> {
            val items = (uiState as UiState.Success<List<CartProductUi>>).data
            CartSuccessState(items)
        }
    }
}

@Composable
fun CartSuccessState(
    items: List<CartProductUi>,
) {
    val navController = LocalRootNavController.current

    Scaffold(
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding(),
                onClick = {
                    navController.navigate(AppRoute.Checkout.path)
                }
            ) {
                Text("Go to checkout")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items.count()) { index ->
                val product = items[index]
                CartProductItem(product)
            }
        }
    }
}

@Composable
fun CartProductItem(product: CartProductUi) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, style = MaterialTheme.typography.titleMedium)
                Text("€${product.price}", style = MaterialTheme.typography.bodySmall)
                Text("Quantity: ${product.quantity}", style = MaterialTheme.typography.bodySmall)
                Text("Total: €${product.totalPrice}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
