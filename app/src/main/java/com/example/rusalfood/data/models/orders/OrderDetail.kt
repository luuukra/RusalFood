package com.example.rusalfood.data.models.orders


import com.google.gson.annotations.SerializedName

data class OrderDetail(
    val id: Int,
    val product: Product,
    val quantity: Int
)