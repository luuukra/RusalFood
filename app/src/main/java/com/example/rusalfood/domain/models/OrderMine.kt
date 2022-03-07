package com.example.rusalfood.domain.models

data class OrderMine(
    val orderId: Int,
    val orderAddress: String,
    val restaurantName: String,
    val totalPrice: Int,
    val orderStatus: String,
    val orderDate: String,
)
