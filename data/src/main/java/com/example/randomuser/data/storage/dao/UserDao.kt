package com.example.randomuser.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.randomuser.data.storage.models.UserInStorage
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun saveUser(user: UserInStorage): Long

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): UserInStorage

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserInStorage>>

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUserById(id: Int)
}