package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.ErrorResponse
import com.example.rusalfood.domain.models.SignInResponse
import com.example.rusalfood.domain.models.User
import com.google.gson.Gson
import javax.inject.Inject


interface SignInUseCase {
    suspend operator fun invoke(login: String, password: String): SignInResponse
}

class SignInUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    SignInUseCase {

    override suspend operator fun invoke(login: String, password: String): SignInResponse {
        val response = loginRepository.getAuthToken(User(login, password))

        return if (response.isSuccessful) {
            loginRepository.putTokenToEncryptedSharedPref(response.body()!!.data.accessToken)
            response.body()!!.mapToSignInResponse(response.code())

        } else {
            val errorResponse =
                Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
            SignInResponse(response.code(), errorResponse.message)
        }
    }
}

