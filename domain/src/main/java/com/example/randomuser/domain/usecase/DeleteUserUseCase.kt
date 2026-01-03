package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.repository.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(id: Int) {
        userRepository.deleteUser(id)
    }
}