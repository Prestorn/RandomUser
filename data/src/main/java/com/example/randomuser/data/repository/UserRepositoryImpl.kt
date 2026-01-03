package com.example.randomuser.data.repository

import com.example.randomuser.data.retrofit.models.UserByServer
import com.example.randomuser.data.retrofit.service.ApiService
import com.example.randomuser.data.storage.AppDB
import com.example.randomuser.data.storage.models.UserInStorage
import com.example.randomuser.domain.models.GenerateParams
import com.example.randomuser.domain.models.UserInDomain
import com.example.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val MAX_SERVER_REQUESTS = 5

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val db: AppDB) : UserRepository {

    override suspend fun getRandomUser(
        params: GenerateParams
    ): UserInDomain? {
        var user: UserByServer? = apiService.getRandomUser(
            gender = params.gender,
            nationality = params.nationality
        )
        var requestCount = 1
        while (user == null && requestCount < MAX_SERVER_REQUESTS) {
            user = apiService.getRandomUser(
                gender = params.gender,
                nationality = params.nationality
            )
            requestCount++
        }
        var userId: Long = -1
        if (user != null) {
            userId = db.userDao.saveUser(user.toStorage())
        }
        return user?.toDomain(userId)
    }

    override suspend fun getUserDetail(id: Int): UserInDomain {
        return db.userDao.getUserById(id).toDomain()
    }

    override fun getUsersList(): Flow<List<UserInDomain>> {
        return db.userDao.getAllUsers().map { userList -> userList.map { it.toDomain() } }
    }

    override suspend fun deleteUser(id: Int) {
        db.userDao.deleteUserById(id)
    }

    private fun UserByServer.toDomain(id: Long): UserInDomain {
        return UserInDomain(
            id = id.toInt(),
            firstName = this.name.first,
            lastName = this.name.last,
            email = this.email,
            birthDate = this.dob.date,
            telephoneNumber = this.phone,
            username = this.login.username,
            password = this.login.password,
            picture = this.picture.large,
            streetName = this.location.street.name,
            streetNumber = this.location.street.number,
            city = this.location.city,
            state = this.location.state,
            country = this.location.country,
            postcode = this.location.postcode,
            gender = this.gender
        )
    }

    private fun UserByServer.toStorage(): UserInStorage {
        return UserInStorage(
            id = 0,
            firstName = this.name.first,
            lastName = this.name.last,
            email = this.email,
            birthDate = this.dob.date.substring(0, 10),
            telephoneNumber = this.phone,
            username = this.login.username,
            password = this.login.password,
            picture = this.picture.large,
            streetName = this.location.street.name,
            streetNumber = this.location.street.number,
            city = this.location.city,
            state = this.location.state,
            country = this.location.country,
            postcode = this.location.postcode,
            gender = this.gender
        )
    }

    private fun UserInStorage.toDomain(): UserInDomain {
        return UserInDomain(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            birthDate = this.birthDate,
            telephoneNumber = this.telephoneNumber,
            username = this.username,
            password = this.password,
            picture = this.picture,
            streetName = this.streetName,
            streetNumber = this.streetNumber,
            city = this.city,
            state = this.state,
            country = this.country,
            postcode = this.postcode,
            gender = this.gender
        )
    }
}