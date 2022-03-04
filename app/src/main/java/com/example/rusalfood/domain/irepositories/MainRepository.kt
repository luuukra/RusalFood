package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import com.example.rusalfood.domain.models.Place
import retrofit2.Response

interface MainRepository {

    suspend fun getAllPlaces(): Response<List<ApiRestaurantModel>>

    suspend fun getPlaceFoodList(placeId: Int): Response<ApiFoodListModel>

    suspend fun getOrdersList(): List<MockOrder>

    suspend fun getOrderById(orderId: Int): MockOrder

}