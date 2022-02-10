package com.example.rusalfood.domain.models

data class Food(
    val foodCategory: String,
    val foodName: String,
    val foodPrice: Int,
    val foodDesc: String,
    val foodImage: String,
)