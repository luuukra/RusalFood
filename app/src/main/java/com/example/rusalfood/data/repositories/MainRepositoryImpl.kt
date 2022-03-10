package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.data.network.MockFoodListData
import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.PreparedOrder
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : MainRepository {

    override suspend fun getAllPlaces() = retrofitService.getAllPlaces()

    override suspend fun getPlaceFoodList(placeId: Int) = retrofitService.getFoodListById(placeId)

    override suspend fun sendOrders(token: String, preparedOrder: PreparedOrder) = retrofitService.sendOrders(token, preparedOrder)

//    override suspend fun getOrders() = retrofitService.getOrders()
}