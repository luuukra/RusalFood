package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject


interface GetIntoPlaceUseCase {
    suspend fun execute(placeId: Int): Place
}

class GetIntoPlaceUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetIntoPlaceUseCase {

    override suspend fun execute(placeId: Int): Place {
        return mainRepository.getIntoPlace(placeId)
    }
}
