package com.example.rusalfood.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.example.rusalfood.data.network.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@Module
class RetrofitModule {

    private val baseURL = "http://142.93.107.238/api/"

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        //verifier всегда возвращает true
        //чтобы приложение не падало из-за ошибки
        //"javax.net.ssl.SSLPeerUnverifiedException: Hostname 142.93.107.238 not verified:"
        val builder = OkHttpClient.Builder()
            .hostnameVerifier(object : HostnameVerifier {
                override fun verify(p0: String?, p1: SSLSession?): Boolean {
                    return true
                }
            })
        val client: OkHttpClient = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

