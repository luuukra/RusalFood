package com.example.rusalfood.domain.irepositories

interface LoginRepository {
    suspend fun getAuthToken(login: String, password: String): String?
    suspend fun checkIsEmailAvailable(email: String): Boolean
    suspend fun signUpAndGetAuthToken(login: String, password: String): String
}