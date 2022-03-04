package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.data.network.IMockOrders
import com.example.rusalfood.data.network.MockFoodListData
import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val mockOrders: IMockOrders,
) : MainRepository {

    override suspend fun getAllPlaces() = retrofitService.getAllPlaces()

    override suspend fun getPlaceFoodList(placeId: Int) = retrofitService.getFoodListById(placeId)


    override suspend fun getOrdersList(): List<MockOrder> {
        return mockOrders.getOrdersList()
    }

    override suspend fun getOrderById(orderId: Int): MockOrder {
        return mockOrders.getOrderById(orderId)
    }


}