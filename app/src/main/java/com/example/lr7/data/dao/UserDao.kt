package com.example.lr7.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lr7.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE userId = :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT name FROM user_table WHERE userId = :id")
    suspend fun getUserNameById(id: Int): String
}