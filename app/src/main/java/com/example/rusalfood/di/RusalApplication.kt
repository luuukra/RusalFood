package com.example.rusalfood.di

import android.app.Application
import android.content.Context


class RusalApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is RusalApplication -> appComponent
        else -> this.applicationContext.appComponent
    }



