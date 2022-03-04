package com.example.rusalfood.data.models

data class MockOrder(
    val orderId: Int,
    val orderAddress: String,
    val restaurantName: String,
    val totalPrice: Int,
    val orderStatus: String,
    val orderDate: String,
)

