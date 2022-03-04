package com.example.rusalfood.di

import com.example.rusalfood.data.repositories.LoginRepositoryImpl
import com.example.rusalfood.data.repositories.MainRepositoryImpl
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.irepositories.MainRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface VMModule {

    @Singleton
    @Binds
    fun bindLoginRepositoryImplToLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    fun bindMainRepositoryImplToMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

}