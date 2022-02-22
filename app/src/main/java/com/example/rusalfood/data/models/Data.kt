package com.example.rusalfood.data.models

data class Data(
    val restaurant_id: Int,
    val restaurant_name: String,
    val location: String,
    val restaurant_images: List<RestaurantImage>,
    val product_categories: List<ProductCategory>
)