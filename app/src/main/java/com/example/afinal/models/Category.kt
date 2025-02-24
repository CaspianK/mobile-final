package com.example.afinal.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryName: String
)