package com.example.lr7.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "order_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    @ColumnInfo(name = "order_details") val orderDetails: String,
    @ColumnInfo(name = "userId") val userId: Long
)
