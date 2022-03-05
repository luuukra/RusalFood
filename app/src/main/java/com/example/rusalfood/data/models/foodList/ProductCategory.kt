package com.example.rusalfood.data.models.foodList

import com.example.rusalfood.domain.models.Food
import com.google.gson.annotations.SerializedName

data class ProductCategory(
    @SerializedName("product_category_id")
    val productCategoryId: Int,
    @SerializedName("product_category_name")
    val productCategoryName: String,
    @SerializedName("products")
    val products: List<Product>
) {
    fun mapToFoodCategory() = Food.FoodCategory(
        categoryName = productCategoryName,
        catFoodAmount = products.size
    )
}