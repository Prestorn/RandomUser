package com.example.randomuser.presentation.models.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLocation(
    val country: String,
    val state: String,
    val city: String,
    val streetName: String,
    val streetNumber: Int,
    val postCode: String
) : Parcelable
