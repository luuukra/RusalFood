package com.example.rusalfood.data.network

interface SignUpHandler {
    fun isEmailNotSignedUp(email: String)

    fun sendSignUpRequest(email: String, password: String)

    fun handleRequestReply(): String
}