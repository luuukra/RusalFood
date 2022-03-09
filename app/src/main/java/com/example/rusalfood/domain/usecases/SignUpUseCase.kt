package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.SignUpResponse
import com.example.rusalfood.domain.models.User
import javax.inject.Inject

interface SignUpUseCase {
    suspend operator fun invoke(
        login: String,
        password: String
    ): SignUpResponse//Response<SignUpResponse>//fixme signIn response token
}

class SignUpUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    SignUpUseCase {

    override suspend operator fun invoke(
        login: String,
        password: String
    ): SignUpResponse {//Response<SignUpResponse> {
        /*val jsonObject = JsonObject()
        jsonObject.addProperty("email", login)
        jsonObject.addProperty("password", password)
        println(jsonObject.toString())*/
        //val userInfo = User(login, password)

        val signUpResponse = loginRepository.signUp(User(login, password))
        //println(signUpResponse.toString())
        //if(signUpResponse.code() == SIGN_UP_OK_CODE) return signUpResponse.message()
        return signUpResponse.body()!!.mapToDomainSignUpResponse(signUpResponse.code())
//todo errorBody????!!!!!!!!!!!!!!!!!!!!!!
    }
}