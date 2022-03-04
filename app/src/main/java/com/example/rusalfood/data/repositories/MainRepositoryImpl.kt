package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.models.foodList.ProductCategory
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.data.network.MockFoodListData
import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
//    private val mockOrders: MockOrders,
    private val mockFoodListData: MockFoodListData,
    private val mockData: MockData
) : MainRepository {

    override suspend fun getAllPlaces() = retrofitService.getAllPlaces()


    override suspend fun getIntoPlace(placeId: Int): Place {
        return mockData.placesList[placeId]
    }

    override suspend fun getPlaceFoodList(placeId: Int) = retrofitService.getFoodListById(placeId)


//    override suspend fun getOrdersList(): List<MockOrder> {
//        return mockOrders.mockOrders
//
//    }
}