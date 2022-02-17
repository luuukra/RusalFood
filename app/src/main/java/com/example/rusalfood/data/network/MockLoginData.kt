package com.example.rusalfood.data.network

import javax.inject.Inject


class MockLoginData @Inject constructor() {
    var userList = mutableMapOf<String, String>(Pair("choco@choco.com", "chocoPass"))
    val mockToken: String = "mockToken"

    fun checkPasswordAndGetToken(login: String, password: String): String? {
        return if (password == userList[login]) mockToken
        else null
    }

    fun checkIsEmailAvailable(email: String): Boolean {
        return !userList.containsKey(email)

    }

    fun signUpAndGetToken(login: String, password: String): String {
        userList[login] = password
        return mockToken
    }

}
