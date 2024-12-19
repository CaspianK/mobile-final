package com.example.afinal.repository

import com.example.afinal.data.CartItemDao
import com.example.afinal.data.ShoppingCartDao
import com.example.afinal.models.CartItem
import com.example.afinal.models.ShoppingCart
import com.example.afinal.network.ShoppingCartApi
import java.time.LocalDateTime

class ShoppingCartRepository(
    private val shoppingCartDao: ShoppingCartDao,
    private val cartItemDao: CartItemDao,
    private val shoppingCartApi: ShoppingCartApi
) {
    suspend fun getOrCreateShoppingCart(userId: Int): ShoppingCart {
        val cachedCart = shoppingCartDao.getShoppingCartByUserId(userId)
        if (cachedCart != null) return cachedCart

        val response = shoppingCartApi.getShoppingCartByUserId(userId)
        return if (response.isSuccessful) {
            response.body()?.also { shoppingCartDao.insertShoppingCart(it) }
                ?: ShoppingCart(userId = userId, createdAt = LocalDateTime.now()).also { shoppingCartDao.insertShoppingCart(it) }
        } else {
            ShoppingCart(userId = userId, createdAt = LocalDateTime.now()).also { shoppingCartDao.insertShoppingCart(it) }
        }
    }

    suspend fun getCartItems(cartId: Int): List<CartItem> {
        return cartItemDao.getCartItemsByCartId(cartId)
    }

    suspend fun addOrUpdateCartItem(cartItem: CartItem): Boolean {
        val response = shoppingCartApi.addOrUpdateCartItem(cartItem)
        return response.isSuccessful
    }

    suspend fun removeCartItem(cartItemId: Int): Boolean {
        val response = shoppingCartApi.removeCartItem(cartItemId)
        return response.isSuccessful
    }
}