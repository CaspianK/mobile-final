package com.example.afinal.network

import com.example.afinal.models.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("m_products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("m_products/{id}")
    suspend fun getProductById(@Query("id") productId: Int): Response<Product>

    @GET("m_products/search")
    suspend fun searchProducts(@Query("query") query: String): Response<List<Product>>
}
