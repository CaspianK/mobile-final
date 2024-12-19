package com.example.afinal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.afinal.models.UserAddress

@Dao
interface UserAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAddress(address: UserAddress)

    @Query("SELECT * FROM user_addresses WHERE userId = :userId")
    suspend fun getAddressesByUserId(userId: Int): List<UserAddress>
}
