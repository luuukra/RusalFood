package com.example.rusalfood.domain.models

sealed class Food {

    data class FoodItem(
        val id: Int,
        val foodName: String,
        val foodPrice: Int,
        val foodDesc: String,
        val foodImage: String,
        var foodAmount: Int = 0,
    ) : Food() {
        fun mapToPreparedProduct() = PreparedProduct(
            id = id,
            quantity = foodAmount,
        )
    }

    data class FoodCategory(
        val categoryName: String,
        val catFoodAmount: Int
    ) : Food()
}