package com.example.rusalfood.di

import com.example.rusalfood.domain.shared_pref.EncryptedSharedPref
import com.example.rusalfood.domain.shared_pref.EncryptedSharedPrefImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface EncryptedSharedPrefModule {

    @Singleton
    @Binds
    fun bindSharedPreferences(encryptedSharedPrefImpl: EncryptedSharedPrefImpl): EncryptedSharedPref
}