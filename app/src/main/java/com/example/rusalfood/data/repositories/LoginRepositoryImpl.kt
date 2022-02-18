package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.network.MockLoginData
import com.example.rusalfood.domain.irepositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor (private val mockLoginData: MockLoginData) : LoginRepository {

    override suspend fun getAuthToken(login: String, password: String): String? {
        return mockLoginData.checkPasswordAndGetToken(login, password)
    }

    override suspend fun checkIsEmailAvailable(email: String): Boolean {
        return mockLoginData.checkIsEmailAvailable(email)
    }

    override suspend fun signUpAndGetAuthToken(login: String, password: String): String {
        return mockLoginData.signUpAndGetToken(login, password)
    }
}