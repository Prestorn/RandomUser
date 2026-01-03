package com.example.randomuser.presentation.models.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserContacts(
    val email: String,
    val telephoneNumber: String
) : Parcelable
