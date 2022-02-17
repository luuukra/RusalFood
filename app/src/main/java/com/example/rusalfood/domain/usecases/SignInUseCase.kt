package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository

class SignInUseCase(private val loginRepository: LoginRepository) {
    companion object {
        const val AUTH_OK = "Authentication successful"
        const val AUTH_ERROR = "Authentication error: incorrect email or login"
    }

    suspend fun signIn(login: String, password: String): String {
        val token: String? = loginRepository.getAuthToken(login, password)
        return if (!token.isNullOrEmpty()) {
            //token to encryptedSharedPref
            AUTH_OK
        } else {
            AUTH_ERROR
        }

    }
}

