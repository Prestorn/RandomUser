package com.example.randomuser.data.retrofit.service

import android.util.Log
import com.example.randomuser.data.retrofit.controllers.UserController
import com.example.randomuser.data.retrofit.models.ServerAnswer
import com.example.randomuser.data.retrofit.models.UserByServer
import retrofit2.Call

class RetrofitService(private val userController: UserController) : ApiService {

    override suspend fun getRandomUser(
        gender: String,
        nationality: String
    ): UserByServer? {
        try {
            val call: Call<ServerAnswer> = userController.getRandomUser(
                gender = gender,
                nationality = nationality)
            val serverAnswer: ServerAnswer = call.execute().body()!!
            return serverAnswer.results[0]
        } catch (e: Exception) {
            Log.e(
                "Request is failed",
                "${e.cause}\n\n${e.message}\n\n${e.stackTrace.joinToString("\n")}"
            )
            return null
        }
    }

}