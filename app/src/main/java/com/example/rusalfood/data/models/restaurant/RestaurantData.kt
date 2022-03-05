package com.example.rusalfood.data.models.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("restaurant_images")
    val restaurantImages: List<RestaurantImage>,
    @SerializedName("updated_at")
    val updatedAt: String
)