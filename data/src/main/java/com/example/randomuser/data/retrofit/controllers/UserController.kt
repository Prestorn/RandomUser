package com.example.randomuser.data.retrofit.controllers

import com.example.randomuser.data.retrofit.models.ServerAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserController {
    @GET("api/")
    fun getRandomUser(@Query("gender") gender: String,
                      @Query("nat") nationality: String): Call<ServerAnswer>
}