package com.example.afinal.repository

import android.content.Context
import com.example.afinal.data.AppDatabase
import com.example.afinal.network.ProductApi
import com.example.afinal.network.RetrofitInstance
import com.example.afinal.network.ShoppingCartApi

fun provideProductRepository(context: Context): ProductRepository {
    val database = AppDatabase.getInstance(context)
    val productApi = RetrofitInstance.instance.create(ProductApi::class.java)
    return ProductRepository(
        productDao = database.productDao(),
        productApi = productApi
    )
}

fun provideShoppingCartRepository(context: Context): ShoppingCartRepository {
    val database = AppDatabase.getInstance(context)
    val shoppingCartApi = RetrofitInstance.instance.create(ShoppingCartApi::class.java)
    return ShoppingCartRepository(
        shoppingCartDao = database.shoppingCartDao(),
        cartItemDao = database.cartItemDao(),
        shoppingCartApi = shoppingCartApi
    )
}