package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface RetrofitService {
    @GET("restaurants")
    suspend fun getAllPlaces(): Response<List<ApiRestaurantModel>>

    @GET("menu/{id}")
    suspend fun getFoodListById(@Path("id") id: Int): Response<ApiFoodListModel>
}