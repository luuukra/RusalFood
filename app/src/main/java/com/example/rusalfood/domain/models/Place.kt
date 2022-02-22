package com.example.rusalfood.domain.models

data class Place(
    val id: Int,
    val name: String,
    val address: String,
    val mainImage: String,
    val gallery: List<String>,
    //val categoryList: List<String>,
    //val foodList: List<Food.FoodItem>,
)
