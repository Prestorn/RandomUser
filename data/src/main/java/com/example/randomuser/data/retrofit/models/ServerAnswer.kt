package com.example.randomuser.data.retrofit.models


data class ServerAnswer(
    val results: List<UserByServer>,
    val info: Info
) {
    data class Info(
        val seed: String,
        val results: Int,
        val page: Int,
        val version: Double
    )
}

