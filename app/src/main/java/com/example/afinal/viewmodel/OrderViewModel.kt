package com.example.afinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.models.Order
import com.example.afinal.network.OrderRequest
import com.example.afinal.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    fun fetchOrders(userId: Int) {
        viewModelScope.launch {
            _orders.value = orderRepository.getOrdersForUser(userId)
        }
    }

    fun placeOrder(orderRequest: OrderRequest) {
        viewModelScope.launch {
            val order = orderRepository.placeOrder(orderRequest)
            if (order != null) {
                fetchOrders(order.userId)
            }
        }
    }
}
