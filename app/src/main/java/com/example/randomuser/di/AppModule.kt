package com.example.randomuser.di

import com.example.randomuser.presentation.generateuser.GenerateViewModel
import com.example.randomuser.presentation.main.MainViewModel
import com.example.randomuser.presentation.userdetails.DetailsViewModel
import com.example.randomuser.presentation.userlist.UsersListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module{
    viewModelOf(::GenerateViewModel)
    viewModelOf(::UsersListViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::MainViewModel)
}