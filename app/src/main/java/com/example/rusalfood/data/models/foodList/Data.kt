package com.example.rusalfood.data.models.foodList

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("product_categories")
    val productCategories: List<ProductCategory>,
    @SerializedName("restaurant_images")
    val restaurantImages: List<RestaurantImage>
)