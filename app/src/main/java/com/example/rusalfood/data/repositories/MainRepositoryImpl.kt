package com.example.rusalfood.data.repositories

import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place

class MainRepositoryImpl(private val mockData: MockData) : MainRepository {

    override suspend fun displayAllPlaces() : List<Place> {
        return mockData.placesList
    }

}