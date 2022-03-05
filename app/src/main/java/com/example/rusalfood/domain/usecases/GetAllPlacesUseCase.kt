package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import com.example.rusalfood.domain.irepositories.MainRepository
import retrofit2.Response
import javax.inject.Inject

interface GetAllPlacesUseCase {
    suspend operator fun invoke(): Response<List<ApiRestaurantModel>>
}

class GetAllPlacesUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetAllPlacesUseCase {

    override suspend operator fun invoke(): Response<List<ApiRestaurantModel>> {
        return mainRepository.getAllPlaces()
    }
}