package com.example.randomuser.presentation.generateuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.models.GenerateParams
import com.example.randomuser.domain.usecase.GenerateUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateViewModel(
    private val generateUserUseCase: GenerateUserUseCase
) : ViewModel() {

    private val _generatedUserId = MutableLiveData<Int>()
    val generatedUserId: LiveData<Int> = _generatedUserId

    fun generateUser(params: GenerateParams) {
        viewModelScope.launch(Dispatchers.IO) {
            val userId: Int = generateUserUseCase.execute(
                params = GenerateParams(
                    gender = params.gender.lowercase(),
                    nationality = codeNationality(params.nationality)
                )
            )
            _generatedUserId.postValue(userId)
        }
    }

    private fun codeNationality(nationality: String): String {
        return when(nationality) {
            "Australia" -> "AU"
            "Brazil" -> "BR"
            "Canada" -> "CA"
            "Switzerland" -> "CH"
            "Germany" -> "DE"
            "Denmark" -> "DK"
            "Spain" -> "ES"
            "Finland" -> "FI"
            "France" -> "FR"
            "United Kingdom" -> "GB"
            "Ireland" -> "IE"
            "India" -> "IN"
            "Iran" -> "IR"
            "Mexico" -> "MX"
            "Netherlands" -> "NL"
            "Norway" -> "NO"
            "New Zealand" -> "NZ"
            "Serbia" -> "RS"
            "Turkey" -> "TR"
            "Ukraine" -> "UA"
            "United States" -> "US"
            else -> ""
        }
    }
}