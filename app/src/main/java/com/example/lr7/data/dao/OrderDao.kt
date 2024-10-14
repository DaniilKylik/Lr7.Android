package com.example.lr7.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lr7.data.model.Order

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("SELECT * FROM order_table")
    fun getAllOrders(): LiveData<List<Order>>

    @Query("SELECT * FROM order_table WHERE orderId = :id")
    suspend fun getOrderById(id: Int): Order

    @Query("SELECT order_details FROM order_table WHERE userId = :id")
    suspend fun getUserOrderDetails(id: Int): List<String>
}