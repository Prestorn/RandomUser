package com.example.randomuser.data.retrofit.service

import com.example.randomuser.data.retrofit.models.UserByServer


interface ApiService {
    suspend fun getRandomUser(gender: String,
                              nationality: String): UserByServer?
}