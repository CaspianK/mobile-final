package com.example.afinal.network

import com.example.afinal.models.CartItem
import com.example.afinal.models.ShoppingCart
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Query

interface ShoppingCartApi {
    @GET("m_shopping-cart")
    suspend fun getShoppingCartByUserId(@Query("userId") userId: Int): Response<ShoppingCart>

    @POST("m_shopping-cart/items")
    suspend fun addOrUpdateCartItem(@Body cartItem: CartItem): Response<Unit>

    @DELETE("m_shopping-cart/items")
    suspend fun removeCartItem(@Query("id") cartItemId: Int): Response<Unit>
}
