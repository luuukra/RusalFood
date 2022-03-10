package com.example.rusalfood.data.models.orders

import com.example.rusalfood.domain.models.OrderMine

data class ApiOrderResponse(
    val `data`: Data,
    val message: String
)