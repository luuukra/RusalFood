package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.restaurant.ApiRestaurantModel
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.models.Resource
import retrofit2.Response
import javax.inject.Inject

interface GetAllPlacesUseCase {
    suspend operator fun invoke(): Resource<List<Place>>
}

class GetAllPlacesUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetAllPlacesUseCase {

    override suspend operator fun invoke(): Resource<List<Place>> {
        val response = mainRepository.getAllPlaces()
        return if (response.isSuccessful) {
            Resource.Success(response.body()!!.map { it.mapToPlace() })
        } else {
            Resource.Error(response.message())
        }
    }
}