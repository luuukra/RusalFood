package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject

interface DisplayAllPlacesUseCase {
    suspend fun execute(): List<Place>
}

class DisplayAllPlacesUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    DisplayAllPlacesUseCase {

    override suspend fun execute(): List<Place> {
        return mainRepository.displayAllPlaces()
    }
}