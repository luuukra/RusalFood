package com.example.rusalfood.domain.irepositories

import com.example.rusalfood.domain.models.Place

interface MainRepository {

    suspend fun displayAllPlaces() : List<Place>

}