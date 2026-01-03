package com.example.randomuser.presentation.userlist

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.models.UserInDomain
import com.example.randomuser.domain.usecase.DeleteUserUseCase
import com.example.randomuser.domain.usecase.GetUsersListUseCase
import com.example.randomuser.presentation.models.UserForList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UsersListViewModel(
    getUsersListUseCase: GetUsersListUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {
    val usersFlow: LiveData<List<UserForList>> =
        getUsersListUseCase.execute()
            .map { users -> users.map { it.toUserForList() } }.asLiveData()
    var listState: Parcelable? = null

    fun deleteUserById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserUseCase.execute(id)
        }
    }

    private fun UserInDomain.toUserForList(): UserForList {
        return UserForList(
            id = this.id,
            name = "${this.firstName} ${this.lastName}",
            telephoneNumber = this.telephoneNumber,
            codedNationality = codeNationality(this.country),
            picture = this.picture
        )
    }

    private fun codeNationality(decodedNationality: String): String {
        return when (decodedNationality) {
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