package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject

class GetIntoPlaceUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend fun execute(placeId: Int) : Place {
        return mainRepository.getIntoPlace(placeId)
    }

}