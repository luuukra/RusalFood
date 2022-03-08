package com.example.rusalfood.data.models.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantImage(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)