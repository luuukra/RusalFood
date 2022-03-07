package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.data.models.signUp.APISignUpResponse
import com.example.rusalfood.domain.models.User
import com.google.gson.JsonObject
import retrofit2.Response

interface LoginRepository {
    suspend fun signUp(userInfo: User): Response<APISignUpResponse>
    suspend fun getAuthToken(login: String, password: String): String?
}