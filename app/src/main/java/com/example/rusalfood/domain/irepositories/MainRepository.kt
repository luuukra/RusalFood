package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.data.models.orders.ApiOrderResponse
import com.example.rusalfood.data.models.orders.OrderMessage
import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import com.example.rusalfood.domain.models.PreparedOrder
import retrofit2.Response

interface MainRepository {

    suspend fun getAllPlaces(): Response<List<ApiRestaurantModel>>

    suspend fun getPlaceFoodList(placeId: Int): Response<ApiFoodListModel>

    suspend fun sendOrders(token: String, preparedOrder: PreparedOrder): Response<OrderMessage>

    suspend fun getOrders(authString: String): Response<ApiOrderResponse>
}