package com.example.randomuser.presentation.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.models.UserInDomain
import com.example.randomuser.domain.usecase.GetUserDetailsUseCase
import com.example.randomuser.presentation.models.details.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    fun loadUserDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _userDetails.postValue(
                getUserDetailsUseCase.execute(id).toDetails()
            )
        }
    }

    private fun UserInDomain.toDetails(): UserDetails {
        return UserDetails(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            birthDate = this.birthDate,
            streetName = this.streetName,
            streetNumber = this.streetNumber,
            city = this.city,
            state = this.state,
            country = this.country,
            postcode = this.postcode,
            telephoneNumber = this.telephoneNumber,
            username = this.username,
            password = this.password,
            picture = this.picture,
            gender = this.gender
        )
    }
}