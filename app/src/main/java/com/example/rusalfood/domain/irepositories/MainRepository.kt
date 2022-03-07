package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.data.models.orders.mock.MockOrder
import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.data.models.orders.Order
import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import retrofit2.Response

interface MainRepository {

    suspend fun getAllPlaces(): Response<List<ApiRestaurantModel>>

    suspend fun getPlaceFoodList(placeId: Int): Response<ApiFoodListModel>

    suspend fun getOrdersByUserId(userId: Int): Response<List<Order>>


    // Mock

//    suspend fun getOrdersByUserId(userId: Int): List<MockOrder>
//
//    suspend fun getOrderDetails(orderId: Int): MockOrder

}