package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.network.MockLoginData
import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.User
import com.google.gson.JsonObject
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val mockLoginData: MockLoginData
) : LoginRepository {

    override suspend fun getAuthToken(login: String, password: String): String? {
        return mockLoginData.checkPasswordAndGetToken(login, password)
    }

    override suspend fun signUp(userInfo: User) = retrofitService.signUp(userInfo)
}