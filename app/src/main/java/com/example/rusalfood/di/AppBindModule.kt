package com.example.rusalfood.di

import com.example.rusalfood.data.repositories.LoginRepositoryImpl
import com.example.rusalfood.data.repositories.MainRepositoryImpl
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.presentation.main_fragment.MainAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindModule {

    @Singleton
    @Binds
    fun bindLoginRepositoryImplToLoginRepository(loginRepositoryImpl: LoginRepositoryImpl) : LoginRepository

    @Singleton
    @Binds
    fun bindMainRepositoryImplToMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository

}