package com.example.lr7.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String
)
