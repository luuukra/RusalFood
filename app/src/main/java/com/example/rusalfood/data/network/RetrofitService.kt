package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import com.example.rusalfood.data.models.signUp.APISignUpResponse
import com.example.rusalfood.domain.models.User
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @Headers("Content-Type:application/json")
    @POST("register")
    suspend fun signUp(@Body user: User): Response<APISignUpResponse>

    @GET("restaurants")
    suspend fun getAllPlaces(): Response<List<ApiRestaurantModel>>

    @GET("menu/{id}")
    suspend fun getFoodListById(@Path("id") id: Int): Response<ApiFoodListModel>
}