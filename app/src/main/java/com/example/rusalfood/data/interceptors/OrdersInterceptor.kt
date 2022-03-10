//package com.example.rusalfood.data.interceptors
//
//import android.content.Context
//import com.example.rusalfood.data.repositories.LoginRepositoryImpl
//import okhttp3.Interceptor
//import okhttp3.Response
//
//class OrdersInterceptor(
//    private val token: String = "",
//) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer Token $token")
//            .build()
//
//        return chain.proceed(request)
//    }
//}