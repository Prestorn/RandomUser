package com.example.randomuser.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserInStorage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val gender: String,
    val email: String,
    @ColumnInfo(name = "birth_date")
    val birthDate: String,
    @ColumnInfo(name = "street_name")
    val streetName: String,
    @ColumnInfo(name = "street_number")
    val streetNumber: Int,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    @ColumnInfo(name = "telephone_number")
    val telephoneNumber: String,
    val username: String,
    val password: String,
    val picture: String
)