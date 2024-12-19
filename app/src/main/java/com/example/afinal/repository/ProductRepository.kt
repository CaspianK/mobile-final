package com.example.afinal.repository

import com.example.afinal.data.ProductDao
import com.example.afinal.models.Product
import com.example.afinal.network.ProductApi

class ProductRepository(
    private val productDao: ProductDao,
    private val productApi: ProductApi
) {
    suspend fun getAllProducts(): List<Product> {
        val cachedProducts = productDao.getAllProducts()
        if (cachedProducts.isNotEmpty()) return cachedProducts

        val response = productApi.getAllProducts()
        return if (response.isSuccessful) {
            response.body()?.onEach { productDao.insertProduct(it) } ?: emptyList()
        } else emptyList()
    }

    suspend fun getProductById(productId: Int): Product? {
        val cachedProduct = productDao.getProductById(productId)
        if (cachedProduct != null) return cachedProduct

        val response = productApi.getProductById(productId)
        return if (response.isSuccessful) {
            response.body()?.also { productDao.insertProduct(it) }
        } else null
    }

    suspend fun searchProducts(query: String): List<Product> {
        val cachedResults = productDao.searchProductsByName(query)
        if (cachedResults.isNotEmpty()) return cachedResults

        val response = productApi.searchProducts(query)
        return if (response.isSuccessful) {
            response.body()?.onEach { productDao.insertProduct(it) } ?: emptyList()
        } else emptyList()
    }
}