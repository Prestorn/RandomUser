package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.models.UserInDomain
import com.example.randomuser.domain.repository.UserRepository

class GetUserDetailsUseCase(private val userRepository: UserRepository) {
    suspend fun execute(id: Int): UserInDomain {
        return userRepository.getUserDetail(id)
    }
}