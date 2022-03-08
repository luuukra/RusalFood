package com.example.rusalfood.data.models.signUp

import com.example.rusalfood.domain.models.SignUpResponse

data class ApiSignUpResponse(
    val message: String
) {
    fun mapToDomainSignUpResponse(code: Int) =
        SignUpResponse(
            code = code,
            message = message
        )
}