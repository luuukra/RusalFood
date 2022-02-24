package com.example.rusalfood.presentation.place_fragment

import com.example.rusalfood.data.models.ProductCategory
import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.domain.models.Food.FoodCategory

object FoodListResponseConverter {

    private var foodItemsAndCategories = listOf<Food>()

    fun toFoodItemsAndCategoriesList(responseList: List<ProductCategory>): List<Food> {
        foodItemsAndCategories = responseList.map {
            val tempFoodItemList = mutableListOf<Food.FoodItem>()
            it.products.forEach { product ->
                tempFoodItemList.add(
                    Food.FoodItem(
                        product.product_id,
                        product.product_name,
                        product.price,
                        product.description,
                        product.image
                    )
                )
            }
            listOf(FoodCategory(it.product_category_name, false)) + tempFoodItemList
        }.flatten()

        return foodItemsAndCategories
    }

    fun toCategoriesIndexesList(): List<Int> {
        return foodItemsAndCategories.withIndex().filterNot {
            it.value is Food.FoodItem
        }.map { it.index }
    }


    fun toCategoriesList(responseList: List<ProductCategory>): List<FoodCategory> {
        return responseList.map {
            FoodCategory(it.product_category_name, false)
        }
    }
}