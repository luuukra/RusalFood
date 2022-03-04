package com.example.rusalfood.data.models.restaurant

data class Image(
    val created_at: String,
    val id: Int,
    val image_url: String,
    val restaurant_id: Int,
    val updated_at: String
)