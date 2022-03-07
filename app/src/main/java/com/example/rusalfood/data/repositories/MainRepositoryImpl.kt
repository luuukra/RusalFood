package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.models.orders.mock.MockOrder
import com.example.rusalfood.data.network.IMockOrders
import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : MainRepository {

    override suspend fun getAllPlaces() = retrofitService.getAllPlaces()

    override suspend fun getPlaceFoodList(placeId: Int) = retrofitService.getFoodListById(placeId)

    override suspend fun getOrdersByUserId(userId: Int) = retrofitService.getOrdersByUserId(userId)





    // Mock

//    override suspend fun getOrdersByUserId(userId: Int): List<MockOrder> {
//        return mockOrders.getOrdersByUserId(userId)
//    }
//
//    override suspend fun getOrderDetails(orderId: Int): MockOrder {
//        return mockOrders.getOrderDetails(orderId)
//    }


}