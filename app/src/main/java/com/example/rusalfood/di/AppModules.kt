package com.example.rusalfood.di

import com.example.rusalfood.data.repositories.MainRepositoryImpl
import com.example.rusalfood.domain.irepositories.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModules {

    @Singleton
    @Provides
    fun provideMainRepository(mainRepository: MainRepositoryImpl) : MainRepository {
        return mainRepository
    }

}