package com.example.rusalfood.di

import android.app.Application
import android.content.Context


class RusalApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        //todo если авторизован, показывать главный экран
        setupDagger()
    }

    private fun setupDagger() {
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is RusalApplication -> appComponent
        else -> this.applicationContext.appComponent
    }
