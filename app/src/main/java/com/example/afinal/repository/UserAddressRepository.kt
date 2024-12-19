package com.example.afinal.repository

import com.example.afinal.data.UserAddressDao
import com.example.afinal.models.UserAddress
import com.example.afinal.network.UserAddressApi

class UserAddressRepository(
    private val userAddressDao: UserAddressDao,
    private val userAddressApi: UserAddressApi
) {
    suspend fun getUserAddresses(userId: Int): List<UserAddress> {
        val cachedAddresses = userAddressDao.getAddressesByUserId(userId)
        if (cachedAddresses.isNotEmpty()) return cachedAddresses

        val response = userAddressApi.getUserAddresses(userId)
        return if (response.isSuccessful) {
            response.body()?.also { addresses ->
                addresses.forEach { userAddressDao.insertUserAddress(it) }
            } ?: emptyList()
        } else emptyList()
    }

    suspend fun addOrUpdateUserAddress(address: UserAddress): Boolean {
        val response = userAddressApi.addOrUpdateUserAddress(address)
        if (response.isSuccessful) {
            userAddressDao.insertUserAddress(address)
            return true
        }
        return false
    }
}