package com.example.rusalfood.domain.models

sealed class Food {

    data class FoodItem(
        val id: Int,
        val foodName: String,
        val foodPrice: Int,
        val foodDesc: String,
        val foodImage: String
    ) : Food()

    data class FoodCategory(
        val id: Int,
        val categoryName: String
    ) : Food()
}