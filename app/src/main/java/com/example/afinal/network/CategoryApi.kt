package com.example.afinal.network

import com.example.afinal.models.Category
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("m_categories")
    suspend fun getAllCategories(): Response<List<Category>>
}
