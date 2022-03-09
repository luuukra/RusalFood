package com.example.rusalfood.domain.usecases

import android.content.SharedPreferences
import androidx.security.crypto.MasterKey
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.SignInResponse
import com.example.rusalfood.domain.models.User
import javax.inject.Inject


interface SignInUseCase {
    suspend operator fun invoke(login: String, password: String): SignInResponse
}

class SignInUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    SignInUseCase {

    override suspend operator fun invoke(login: String, password: String): SignInResponse {
        val response = loginRepository.getAuthToken(User(login, password))
        if(response.isSuccessful) {
            loginRepository.putTokenToEncryptedSharedPref(response.body()!!.data.accessToken)
        }
        return response.body()!!.mapToSignInResponse(response.code())


       /* return if (token != ERROR_CODE) {
            //token to encryptedSharedPref
            AUTH_OK
        } else {
            AUTH_ERROR
        }*/
    }
}

