package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.data.models.orders.ApiOrderResponse
import com.example.rusalfood.data.models.orders.OrderMessage
import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import com.example.rusalfood.data.models.signUp.ApiSignUpResponse
import com.example.rusalfood.data.models.user.ApiSignInResponse
import com.example.rusalfood.domain.models.PreparedOrder
import com.example.rusalfood.domain.models.User
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @Headers("Content-Type:application/json")
    @POST("register")
    suspend fun signUp(@Body user: User): Response<ApiSignUpResponse>

    @Headers("Content-Type:application/json")
    @POST("login")
    suspend fun auth(@Body user: User): Response<ApiSignInResponse>

    @GET("restaurants")
    suspend fun getAllPlaces(): Response<List<ApiRestaurantModel>>

    @GET("menu/{id}")
    suspend fun getFoodListById(@Path("id") id: Int): Response<ApiFoodListModel>

    @Headers("Content-Type:application/json")
    @POST("orders/")
    suspend fun sendOrders(
        @Header("Authorization") authString: String,
        @Body preparedOrder: PreparedOrder
    ): Response<OrderMessage>

    @GET("orders")
    suspend fun getOrders(
        @Header("Authorization") authString: String,
    ): Response<List<ApiOrderResponse>>
}
