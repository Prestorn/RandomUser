package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.models.GenerateParams
import com.example.randomuser.domain.models.UserInDomain
import com.example.randomuser.domain.repository.UserRepository

class GenerateUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(
        params: GenerateParams
    ): Int {
        val user: UserInDomain? = userRepository.getRandomUser(params)
        return user?.id ?: -1
    }
}