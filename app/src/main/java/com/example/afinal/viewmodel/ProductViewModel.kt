package com.example.afinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.models.Product
import com.example.afinal.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    fun fetchProducts() {
        viewModelScope.launch {
            _products.value = productRepository.getAllProducts()
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _products.value = productRepository.searchProducts(query)
        }
    }

    fun fetchProductDetails(productId: Int) {
        viewModelScope.launch {
            _selectedProduct.value = productRepository.getProductById(productId)
        }
    }
}
