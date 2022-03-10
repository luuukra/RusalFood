package com.example.rusalfood.data.models.orders


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: Int,
    @SerializedName("product_category_id")
    val productCategoryId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)