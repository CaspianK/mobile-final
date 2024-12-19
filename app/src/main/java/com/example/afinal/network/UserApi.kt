package com.example.afinal.network

import com.example.afinal.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @POST("m_users")
    suspend fun registerUser(@Body user: User): Response<User>

    @GET("m_users")
    suspend fun getUserByEmail(@Query("email") email: String): Response<User>

    @GET("m_users/{id}")
    suspend fun getUserById(@Query("id") userId: Int): Response<User>
}
