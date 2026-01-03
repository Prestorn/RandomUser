package com.example.randomuser.data

import com.example.randomuser.data.retrofit.models.UserByServer
import com.example.randomuser.domain.models.UserInDomain
import org.junit.Assert.*
import org.junit.Test


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val user: UserByServer? = null
        assertEquals(null, user?.toDomain())
    }
}

private fun UserByServer.toDomain(): UserInDomain {
    return UserInDomain(
        id = 1,
        firstName = this.name.first,
        lastName = this.name.last,
        email = this.email,
        birthDate = this.dob.date,
        location = UserInDomain.Location(
            street = UserInDomain.Location.Street(
                number = this.location.street.number,
                name = this.location.street.name
            ),
            city = this.location.city,
            state = this.location.state,
            country = this.location.country,
            postcode = this.location.postcode
        ),
        telephoneNumber = this.phone,
        userName = this.login.username,
        password = this.login.password,
        picture = this.picture.medium
    )
}
