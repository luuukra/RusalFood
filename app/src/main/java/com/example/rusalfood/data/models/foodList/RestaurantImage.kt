package com.example.rusalfood.data.models.foodList

import com.google.gson.annotations.SerializedName

data class RestaurantImage(
    @SerializedName("image_id")
    val imageId: Int,
    @SerializedName("url")
    val url: String
)