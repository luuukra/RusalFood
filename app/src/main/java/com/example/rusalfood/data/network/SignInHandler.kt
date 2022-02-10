package com.example.rusalfood.data.network

interface SignInHandler {
    fun sendSignInRequest(email: String, password: String)

    fun handleRequestReply(): String
}