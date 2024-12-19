package com.example.afinal.network

import com.example.afinal.models.Order
import com.example.afinal.models.OrderItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderApi {
    @POST("m_orders")
    suspend fun placeOrder(@Body orderRequest: OrderRequest): Response<Order>

    @GET("m_orders")
    suspend fun getOrdersForUser(@Query("userId") userId: Int): Response<List<Order>>

    @GET("m_orders/{id}")
    suspend fun getOrderById(@Query("id") orderId: Int): Response<Order>

    @GET("m_orders/details")
    suspend fun getOrderDetails(@Query("id") orderId: Int): Response<OrderItem>
}
