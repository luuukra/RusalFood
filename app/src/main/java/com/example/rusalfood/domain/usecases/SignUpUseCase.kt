package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository

class SignUpUseCase(private val loginRepository: LoginRepository) {
    companion object {
        const val AUTH_OK = "Registration and Authentication successful"
        //const val AUTH_ERROR = "Authentication error: incorrect email or login"
    }

    suspend fun checkEmail(email: String): Boolean {
        return loginRepository.checkIsEmailAvailable(email)
    }

    suspend fun signUpAndGetToken(login: String, password: String): String {
        val token = loginRepository.signUpAndGetAuthToken(login, password)
        //todo token to encryptedSharedPref
        return AUTH_OK
        /*if (!token.isNullOrEmpty()) {
            User.isAuthorized = true
            //token to encryptedSharedPref
            return SignInUseCase.AUTH_OK
        } else {
            return SignInUseCase.AUTH_ERROR
        }*/
    }
}