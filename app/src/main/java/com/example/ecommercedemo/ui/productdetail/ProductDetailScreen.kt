package com.example.ecommercedemo.ui.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommercedemo.ui.cart.CartViewModel
import com.example.ecommercedemo.ui.productdetail.model.ProductDetailUi
import com.example.ecommercedemo.ui.shared.UiState
import com.example.ecommercedemo.ui.shared.components.QuantityPicker
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int?,
    productDetailViewModel: ProductDetailViewModel = koinViewModel(parameters = {
        parametersOf(
            productId
        )
    }),
    cartViewModel: CartViewModel = koinViewModel(),
) {
    val uiState by productDetailViewModel.uiState.collectAsState()
    var quantity by remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit) {
        productDetailViewModel.loadProduct()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Product Detail") }) },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp)
                    .height(56.dp),
                onClick = {
                    productId?.let {
                        cartViewModel.insertProductToCart(productId, quantity)
                    }
                },
            ) {
                Text("Add to Cart")
            }
        }
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
                    SuccessState(product, quantity, { quantity = it })
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
private fun SuccessState(
    product: ProductDetailUi?,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = this.maxWidth > 600.dp

        if (isTablet) {
            TabletProductDetailContent(product, quantity, onQuantityChange)
        } else {
            PhoneProductDetailContent(product, quantity, onQuantityChange)
        }
    }


}

@Composable
fun PhoneProductDetailContent(
    product: ProductDetailUi?,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
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
                onIncrease = { onQuantityChange(quantity + 1) },
                onDecrease = { if (quantity > 1) onQuantityChange(quantity - 1) },
            )
        }
    }
}

@Composable
fun TabletProductDetailContent(
    product: ProductDetailUi?,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    product?.let {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            AsyncImage(
                model = it.image,
                contentDescription = it.name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
                Text(text = "Category: ${it.category}", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = "Price: €${it.price}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)

                QuantityPicker(
                    quantity = quantity,
                    onIncrease = { onQuantityChange(quantity + 1) },
                    onDecrease = { if (quantity > 1) onQuantityChange(quantity - 1) },
                )
            }
        }
    }
}

