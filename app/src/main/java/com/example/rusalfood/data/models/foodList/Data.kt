package com.example.rusalfood.data.models.foodList

data class Data(
    val restaurant_id: Int,
    val restaurant_name: String,
    val location: String,
    val product_categories: List<ProductCategory>,
    val restaurant_images: List<RestaurantImage>
)