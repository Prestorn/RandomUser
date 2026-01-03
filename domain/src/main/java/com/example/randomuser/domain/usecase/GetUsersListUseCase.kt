package com.example.randomuser.domain.usecase

import com.example.randomuser.domain.models.UserInDomain
import com.example.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersListUseCase(private val userRepository: UserRepository) {
    fun execute(): Flow<List<UserInDomain>> {
        return userRepository.getUsersList()
    }
}