package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.ProductCategory
import com.example.rusalfood.domain.irepositories.MainRepository
import javax.inject.Inject

interface GetFoodListUseCase {
    suspend fun getFoodList(placeId: Int): List<ProductCategory>
}

class GetFoodListUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetFoodListUseCase {
    override suspend fun getFoodList(placeId: Int): List<ProductCategory> {
        return mainRepository.getPlaceFoodList(placeId)
    }
}