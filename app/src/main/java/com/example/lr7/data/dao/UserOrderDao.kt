package com.example.lr7.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserOrderDao {
    @Query("SELECT user_table.name AS name, order_table.order_details AS order_details FROM user_table INNER JOIN order_table ON user_table.userId = order_table.userId")
    fun getUserOrders(): LiveData<List<UserOrder>>

    data class UserOrder(
        val name: String,
        val order_details: String
    )
}