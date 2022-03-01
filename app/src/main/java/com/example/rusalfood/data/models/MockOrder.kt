package com.example.rusalfood.data.models

data class MockOrder(
    val order_id: Int,
    val restaurant_name: String,
    val total_price: Int,
    val order_status: String,
    val order_date: String,
)
