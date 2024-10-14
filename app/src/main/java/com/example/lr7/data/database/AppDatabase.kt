package com.example.lr7.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lr7.data.model.User
import com.example.lr7.data.model.Order
import com.example.lr7.data.dao.UserDao
import com.example.lr7.data.dao.OrderDao
import com.example.lr7.data.dao.UserOrderDao

@Database(entities = [User::class, Order::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao
    abstract fun userOrderDao(): UserOrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}