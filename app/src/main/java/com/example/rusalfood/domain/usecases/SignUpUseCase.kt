package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.ErrorResponse
import com.example.rusalfood.domain.models.SignUpResponse
import com.example.rusalfood.domain.models.User
import com.google.gson.Gson
import javax.inject.Inject

interface SignUpUseCase {
    suspend operator fun invoke(
        login: String,
        password: String
    ): SignUpResponse
}

class SignUpUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    SignUpUseCase {

    override suspend operator fun invoke(
        login: String,
        password: String
    ): SignUpResponse {

        val signUpResponse = loginRepository.signUp(User(login, password))

        return if (signUpResponse.isSuccessful) {
            signUpResponse.body()!!.mapToDomainSignUpResponse(signUpResponse.code())
        } else {
            val errorResponse =
                Gson().fromJson(
                    signUpResponse.errorBody()!!.charStream(),
                    ErrorResponse::class.java
                )
            SignUpResponse(signUpResponse.code(), errorResponse.message)

        }
    }
}