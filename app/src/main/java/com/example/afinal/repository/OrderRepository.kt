package com.example.afinal.repository

import com.example.afinal.data.OrderDao
import com.example.afinal.models.Order
import com.example.afinal.models.OrderItem
import com.example.afinal.network.OrderApi
import com.example.afinal.network.OrderRequest

class OrderRepository(
    private val orderDao: OrderDao,
    private val orderApi: OrderApi
) {
    suspend fun placeOrder(orderRequest: OrderRequest): Order? {
        val response = orderApi.placeOrder(orderRequest)
        return if (response.isSuccessful) {
            response.body()?.also { orderDao.insertOrder(it) }
        } else null
    }

    suspend fun getOrdersForUser(userId: Int): List<Order> {
        val cachedOrders = orderDao.getOrdersByUserId(userId)
        if (cachedOrders.isNotEmpty()) return cachedOrders

        val response = orderApi.getOrdersForUser(userId)
        return if (response.isSuccessful) {
            response.body()?.onEach { orderDao.insertOrder(it) } ?: emptyList()
        } else emptyList()
    }

    suspend fun getOrderById(orderId: Int): Order? {
        val cachedOrder = orderDao.getOrderById(orderId)
        if (cachedOrder != null) return cachedOrder

        val response = orderApi.getOrderById(orderId)
        return if (response.isSuccessful) {
            response.body()?.also { orderDao.insertOrder(it) }
        } else null
    }

    suspend fun getOrderDetails(orderId: Int): OrderItem? {
        val response = orderApi.getOrderDetails(orderId)
        return if (response.isSuccessful) {
            response.body()
        } else null
    }
}

