package com.example.rusalfood.data.models.foodList

import com.example.rusalfood.domain.models.Food

data class Product(
    val product_id: Int,
    val product_name: String,
    val description: String,
    val image: String,
    val price: Int
) {
    fun mapToFoodItem() = Food.FoodItem(
        id = product_id,
        foodName = product_name,
        foodPrice = price,
        foodDesc = description,
        foodImage = image,
        foodAmount = 0
    )
}