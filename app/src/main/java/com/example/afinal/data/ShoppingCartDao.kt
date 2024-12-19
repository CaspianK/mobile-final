package com.example.afinal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.afinal.models.ShoppingCart

@Dao
interface ShoppingCartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingCart(cart: ShoppingCart)

    @Query("SELECT * FROM shopping_carts WHERE userId = :userId")
    suspend fun getShoppingCartByUserId(userId: Int): ShoppingCart?
}
