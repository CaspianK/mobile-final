package com.example.afinal.repository

import com.example.afinal.data.PaymentDao
import com.example.afinal.models.Payment
import com.example.afinal.network.PaymentApi

class PaymentRepository(
    private val paymentDao: PaymentDao,
    private val paymentApi: PaymentApi
) {
    suspend fun getPaymentDetails(orderId: Int): List<Payment> {
        val cachedPayment = paymentDao.getPaymentByOrderId(orderId)
        if (cachedPayment != null) return listOf(cachedPayment)

        val response = paymentApi.getPaymentDetails(orderId)
        return if (response.isSuccessful) {
            response.body()?.onEach { paymentDao.insertPayment(it) } ?: emptyList()
        } else emptyList()
    }
}