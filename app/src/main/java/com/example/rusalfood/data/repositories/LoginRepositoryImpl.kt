package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : LoginRepository {

    override suspend fun getAuthToken(userInfo: User) = retrofitService.auth(userInfo)

    override suspend fun signUp(userInfo: User) = retrofitService.signUp(userInfo)
}