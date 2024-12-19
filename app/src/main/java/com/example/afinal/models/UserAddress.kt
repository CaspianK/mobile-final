package com.example.afinal.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_addresses",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])
    ]
)
data class UserAddress(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String
)