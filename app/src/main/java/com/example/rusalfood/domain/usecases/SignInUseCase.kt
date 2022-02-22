package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository
import javax.inject.Inject

interface SignInUseCase {
    suspend fun signIn(login: String, password: String): String
}

class SignInUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    SignInUseCase {

    companion object {
        const val AUTH_OK = "Authentication successful"
        const val AUTH_ERROR = "Authentication error: incorrect email or login"
    }

    override suspend fun signIn(login: String, password: String): String {
        val token: String? = loginRepository.getAuthToken(login, password)
        return if (!token.isNullOrEmpty()) {
            //token to encryptedSharedPref
            AUTH_OK
        } else {
            AUTH_ERROR
        }

    }
}

