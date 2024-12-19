package com.example.afinal.network

import com.example.afinal.models.Order
import com.example.afinal.models.OrderItem

data class OrderRequest(
    val order: Order,
    val orderItems: List<OrderItem>
)