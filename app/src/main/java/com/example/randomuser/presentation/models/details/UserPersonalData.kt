package com.example.randomuser.presentation.models.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPersonalData (
    val firstName: String,
    val lastName: String,
    val gender: String,
    val dateOfBirth: String,
    val age: Int
) : Parcelable