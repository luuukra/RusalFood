package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun checkEmail(email: String): Boolean

    suspend fun signUpAndGetToken(login: String, password: String): String
}

class SignUpUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    SignUpUseCase {

    companion object {
        const val AUTH_OK = "Registration and Authentication successful"
        //const val AUTH_ERROR = "Authentication error: incorrect email or login"
    }

    override suspend fun checkEmail(email: String): Boolean {
        return loginRepository.checkIsEmailAvailable(email)
    }

    override suspend fun signUpAndGetToken(login: String, password: String): String {
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
