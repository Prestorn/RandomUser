package com.example.randomuser.domain.repository

import com.example.randomuser.domain.models.GenerateParams
import com.example.randomuser.domain.models.UserInDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getRandomUser(
        params: GenerateParams
    ): UserInDomain?

    suspend fun getUserDetail(id: Int): UserInDomain
    fun getUsersList(): Flow<List<UserInDomain>>

    suspend fun deleteUser(id: Int)
}