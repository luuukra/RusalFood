package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place

class DisplayAllPlacesUseCase(private val mainRepository: MainRepository) {

    suspend fun execute() : List<Place> {
        return mainRepository.displayAllPlaces()
    }

}