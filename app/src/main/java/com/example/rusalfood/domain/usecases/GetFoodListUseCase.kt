package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.foodList.ApiFoodListModel
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Food
import retrofit2.Response
import javax.inject.Inject

interface GetFoodListUseCase {
    suspend fun getFoodAndCategoriesList(placeId: Int): Pair<List<Food>, List<Food.FoodCategory>>
}

class GetFoodListUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetFoodListUseCase {
    override suspend fun getFoodAndCategoriesList(placeId: Int): Pair<List<Food>, List<Food.FoodCategory>> {
        return responseListToFoodAndFoodCatList(mainRepository.getPlaceFoodList(placeId))
    }

    private fun responseListToFoodAndFoodCatList(responseList: Response<ApiFoodListModel>): Pair<List<Food>, List<Food.FoodCategory>> {
        return Pair(toFoodItemsAndCategoriesList(responseList), toCategoriesList(responseList))
    }

    private fun toFoodItemsAndCategoriesList(responseList: Response<ApiFoodListModel>): List<Food> {
        return responseList.body()!!.data.product_categories.map {
            val tempFoodItemList = mutableListOf<Food.FoodItem>()
            it.products.forEach { product ->
                tempFoodItemList.add(
                    product.mapToFoodItem()
                )
            }
            listOf(it.mapToFoodCategory()) + tempFoodItemList
        }.flatten()

    }

    private fun toCategoriesList(responseList: Response<ApiFoodListModel>): List<Food.FoodCategory> {
        return responseList.body()!!.data.product_categories.map {
            it.mapToFoodCategory()
        }
    }
}