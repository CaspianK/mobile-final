package com.example.afinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.afinal.viewmodel.ProductViewModel
import com.example.afinal.viewmodel.ShoppingCartViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    viewModel: ProductViewModel,
    cartViewModel: ShoppingCartViewModel,
    onAddToCart: (Any?) -> Unit
) {
    val product by viewModel.selectedProduct.collectAsState()

    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }

    product?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = it.name, style = MaterialTheme.typography.titleMedium)
            Image(
                painter = rememberAsyncImagePainter(it.imageUrl),
                contentDescription = it.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(text = it.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price: $${it.price}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    cartViewModel.addToCart(productId = it.id)
                    onAddToCart()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add to Cart")
            }
        }
    }
}