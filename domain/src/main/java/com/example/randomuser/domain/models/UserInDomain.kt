package com.example.randomuser.domain.models

data class UserInDomain(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String,
    val streetName: String,
    val streetNumber: Int,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val telephoneNumber: String,
    val username: String,
    val password: String,
    val picture: String,
    val gender: String
)