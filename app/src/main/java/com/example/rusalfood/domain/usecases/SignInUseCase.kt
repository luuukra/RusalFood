package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository
import javax.inject.Inject

//Constructor-Inject
interface SignInUseCase {
    suspend operator fun invoke(login: String, password: String): String
}

class SignInUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
    ) : SignInUseCase {



    companion object {
        const val AUTH_OK = "Authentication successful"
        const val AUTH_ERROR = "Authentication error: incorrect email or login"
        const val ERROR_CODE = "0"
    }

    override suspend operator fun invoke(login: String, password: String): String {
        val token = loginRepository.getAuthToken(login, password)
        return if (token != ERROR_CODE) {
            //token to encryptedSharedPref
            AUTH_OK
        } else {
            AUTH_ERROR
        }

    }
}

