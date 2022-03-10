package com.example.rusalfood.data.repositories

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.User
import com.example.rusalfood.domain.shared_pref.EncryptedSharedPref
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val encryptedSharedPref: EncryptedSharedPref
) : LoginRepository {

    override suspend fun getAuthToken(userInfo: User) = retrofitService.auth(userInfo)

    override suspend fun signUp(userInfo: User) = retrofitService.signUp(userInfo)

    override suspend fun putTokenToEncryptedSharedPref(token: String) {
        encryptedSharedPref.putString("token", token)
    }

    override suspend fun getTokenFromEncryptedSharedPref(): String? {
        return encryptedSharedPref.getString("token", "")
    }
}