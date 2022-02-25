package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.data.models.ProductCategory
import com.example.rusalfood.domain.models.Place

interface MainRepository {

    suspend fun displayAllPlaces(): List<Place>

    suspend fun getIntoPlace(placeId: Int): Place

    suspend fun getPlaceFoodList(placeId: Int): List<ProductCategory>

}