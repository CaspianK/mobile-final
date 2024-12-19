package com.example.afinal.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "shopping_carts",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])
    ]
)
data class ShoppingCart(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val createdAt: LocalDateTime
)