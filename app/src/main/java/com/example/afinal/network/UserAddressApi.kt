package com.example.afinal.network

import com.example.afinal.models.UserAddress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAddressApi {
    @GET("m_user-addresses")
    suspend fun getUserAddresses(@Query("userId") userId: Int): Response<List<UserAddress>>

    @POST("m_user-addresses")
    suspend fun addOrUpdateUserAddress(@Body address: UserAddress): Response<Unit>
}
