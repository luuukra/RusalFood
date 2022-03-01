package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.data.models.ProductCategory
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.data.network.MockFoodListData
import com.example.rusalfood.data.network.MockOrders
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mockData: MockData,
//    private val mockOrders: MockOrders,
    private val mockFoodListData: MockFoodListData
) : MainRepository {

    override suspend fun displayAllPlaces(): List<Place> {
        return mockData.placesList
    }

    override suspend fun getIntoPlace(placeId: Int): Place {
        return mockData.placesList[placeId]
    }

    override suspend fun getPlaceFoodList(placeId: Int): List<ProductCategory> {
        return mockFoodListData.foodListByPlaceId[placeId]!!
    }

//    override suspend fun getOrdersList(): List<MockOrder> {
//        return mockOrders.mockOrders
//
//    }


}