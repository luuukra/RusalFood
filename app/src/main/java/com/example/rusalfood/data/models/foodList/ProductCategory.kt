package com.example.rusalfood.data.models.foodList

import com.example.rusalfood.domain.models.Food

data class ProductCategory(
    val product_category_id: Int,
    val product_category_name: String,
    val products: List<Product>
) {
    fun mapToFoodCategory() = Food.FoodCategory(
        categoryName = product_category_name,
        catFoodAmount = products.size
    )
}