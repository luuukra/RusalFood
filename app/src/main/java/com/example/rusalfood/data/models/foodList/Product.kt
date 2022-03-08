package com.example.rusalfood.data.models.foodList

import com.example.rusalfood.domain.models.Food
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Int
) {
    fun mapToFoodItem() = Food.FoodItem(
        id = productId,
        foodName = productName,
        foodPrice = price,
        foodDesc = description,
        foodImage = image,
        foodAmount = 0
    )
}