package com.example.afinal.repository

import com.example.afinal.data.UserDao
import com.example.afinal.models.User
import com.example.afinal.network.UserApi

class UserRepository(
    private val userDao: UserDao,
    private val userApi: UserApi
) {
    suspend fun registerUser(user: User): User? {
        val response = userApi.registerUser(user)
        return if (response.isSuccessful) {
            response.body()?.also { userDao.insertUser(it) }
        } else null
    }

    suspend fun getUserByEmail(email: String): User? {
        val cachedUser = userDao.getUserByEmail(email)
        if (cachedUser != null) return cachedUser

        val response = userApi.getUserByEmail(email)
        return if (response.isSuccessful) {
            response.body()?.also { userDao.insertUser(it) }
        } else null
    }

    suspend fun getUserById(userId: Int): User? {
        val cachedUser = userDao.getUserById(userId)
        if (cachedUser != null) return cachedUser

        val response = userApi.getUserById(userId)
        return if (response.isSuccessful) {
            response.body()?.also { userDao.insertUser(it) }
        } else null
    }
}