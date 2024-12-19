package com.example.afinal.repository

import com.example.afinal.data.CategoryDao
import com.example.afinal.models.Category
import com.example.afinal.network.CategoryApi

class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val categoryApi: CategoryApi
) {
    suspend fun getAllCategories(): List<Category> {
        val cachedCategories = categoryDao.getAllCategories()
        if (cachedCategories.isNotEmpty()) return cachedCategories

        val response = categoryApi.getAllCategories()
        return if (response.isSuccessful) {
            response.body()?.onEach { categoryDao.insertCategory(it) } ?: emptyList()
        } else emptyList()
    }
}
