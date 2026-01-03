package com.example.randomuser.di

import com.example.randomuser.domain.usecase.DeleteUserUseCase
import com.example.randomuser.domain.usecase.GenerateUserUseCase
import com.example.randomuser.domain.usecase.GetUserDetailsUseCase
import com.example.randomuser.domain.usecase.GetUsersListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GenerateUserUseCase)
    factoryOf(::GetUserDetailsUseCase)
    factoryOf(::GetUsersListUseCase)
    factoryOf(::DeleteUserUseCase)
}