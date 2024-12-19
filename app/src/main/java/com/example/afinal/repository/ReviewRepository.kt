package com.example.afinal.repository

import com.example.afinal.data.ReviewDao
import com.example.afinal.models.Review
import com.example.afinal.network.ReviewApi

class ReviewRepository(
    private val reviewDao: ReviewDao,
    private val reviewApi: ReviewApi
) {
    suspend fun getReviewsForProduct(productId: Int): List<Review> {
        val cachedReviews = reviewDao.getReviewsByProductId(productId)
        if (cachedReviews.isNotEmpty()) return cachedReviews

        val response = reviewApi.getReviewsForProduct(productId)
        return if (response.isSuccessful) {
            response.body()?.onEach { reviewDao.insertReview(it) } ?: emptyList()
        } else emptyList()
    }

    suspend fun addReview(review: Review): Boolean {
        val response = reviewApi.addReview(review)
        if (response.isSuccessful) {
            reviewDao.insertReview(review)
            return true
        }
        return false
    }
}