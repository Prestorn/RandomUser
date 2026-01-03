package com.example.randomuser.presentation.main

import android.os.Parcelable
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val scrollStates = mutableMapOf<String, Parcelable?>()

}