package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.ProductCategory
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Food
import javax.inject.Inject

interface GetFoodListUseCase {
    suspend fun getFoodAndCategoriesList(placeId: Int): Pair<List<Food>, List<Food.FoodCategory>>
}

class GetFoodListUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetFoodListUseCase {
    override suspend fun getFoodAndCategoriesList(placeId: Int): Pair<List<Food>, List<Food.FoodCategory>> {
        return responseListToFoodAndFoodCatList(mainRepository.getPlaceFoodList(placeId))
    }

    private fun responseListToFoodAndFoodCatList(responseList: List<ProductCategory>): Pair<List<Food>, List<Food.FoodCategory>> {
        return Pair(toFoodItemsAndCategoriesList(responseList), toCategoriesList(responseList))
    }

    private fun toFoodItemsAndCategoriesList(responseList: List<ProductCategory>): List<Food> {
        return responseList.map {
            val tempFoodItemList = mutableListOf<Food.FoodItem>()
            it.products.forEach { product ->
                tempFoodItemList.add(
                    product.mapToFoodItem()
                )
            }
            listOf(it.mapToFoodCategory()) + tempFoodItemList
        }.flatten()
    }

    private fun toCategoriesList(responseList: List<ProductCategory>): List<Food.FoodCategory> {
        return responseList.map {
            it.mapToFoodCategory()
        }
    }
}