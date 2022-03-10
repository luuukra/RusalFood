package com.example.rusalfood.domain.models

data class PreparedOrder(
    val restaurant_id: Int,
    val products: List<PreparedProduct>
)

data class PreparedProduct(
    val id: Int,
    val quantity: Int,
)

