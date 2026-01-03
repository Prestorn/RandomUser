package com.example.randomuser.presentation.models.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLogin(
    val username: String,
    val password: String
) : Parcelable
