package com.example.afinal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.afinal.models.CartItem
import com.example.afinal.models.Category
import com.example.afinal.models.Order
import com.example.afinal.models.OrderItem
import com.example.afinal.models.Payment
import com.example.afinal.models.Product
import com.example.afinal.models.Review
import com.example.afinal.models.ShoppingCart
import com.example.afinal.models.User
import com.example.afinal.models.UserAddress

@Database(
    entities = [
        User::class,
        Product::class,
        Category::class,
        Order::class,
        OrderItem::class,
        ShoppingCart::class,
        CartItem::class,
        Review::class,
        UserAddress::class,
        Payment::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun shoppingCartDao(): ShoppingCartDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun reviewDao(): ReviewDao
    abstract fun userAddressDao(): UserAddressDao
    abstract fun paymentDao(): PaymentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
