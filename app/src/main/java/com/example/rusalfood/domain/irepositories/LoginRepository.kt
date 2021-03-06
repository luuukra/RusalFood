package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.data.models.signUp.ApiSignUpResponse
import com.example.rusalfood.data.models.user.ApiSignInResponse
import com.example.rusalfood.domain.models.User
import retrofit2.Response

interface LoginRepository {
    suspend fun signUp(userInfo: User): Response<ApiSignUpResponse>
    suspend fun getAuthToken(userInfo: User): Response<ApiSignInResponse>
    suspend fun putTokenToEncryptedSharedPref(token: String)
    suspend fun getTokenFromEncryptedSharedPref(): String?
}