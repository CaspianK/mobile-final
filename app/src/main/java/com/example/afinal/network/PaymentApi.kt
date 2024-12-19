package com.example.afinal.network

import com.example.afinal.models.Payment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentApi {
    @GET("m_payment")
    suspend fun getPaymentDetails(@Query("orderId") orderId: Int): Response<List<Payment>>
}
