package com.example.afinal.network

import com.example.afinal.models.Review
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReviewApi {
    @GET("m_reviews")
    suspend fun getReviewsForProduct(@Query("productId") productId: Int): Response<List<Review>>

    @POST("m_reviews")
    suspend fun addReview(@Body review: Review): Response<Unit>
}
