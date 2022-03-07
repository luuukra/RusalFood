package com.example.rusalfood.di

import com.example.rusalfood.data.network.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@Module
class RetrofitModule {

    private val baseURL = "http://142.93.107.238/api/"
    //"http://142.93.107.238:8003/api/"

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
        val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = builder
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


}