package com.example.rusalfood.data.models.orders


import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    val location: String,
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)