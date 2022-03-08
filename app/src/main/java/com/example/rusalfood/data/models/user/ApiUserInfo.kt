package com.example.rusalfood.data.models.user

import com.example.rusalfood.domain.models.SignInResponse
import com.google.gson.annotations.SerializedName

data class ApiUserInfo(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String
) {
    fun mapToSignInResponse(code: Int): SignInResponse {
        return SignInResponse(
            code,
            message = message
        )
    }
}