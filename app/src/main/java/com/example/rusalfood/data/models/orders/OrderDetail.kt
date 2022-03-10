package com.example.rusalfood.data.models.orders

data class OrderDetail(
    val created_at: String,
    val id: Int,
    val order_id: Int,
    val product_id: Int,
    val quantity: Int,
    val updated_at: String
)