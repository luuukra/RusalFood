package com.example.rusalfood.data.models.orders

import com.example.rusalfood.domain.models.PostOrderResponse

data class OrderMessage(
    val message: String
) {
    fun mapToSendOrderResponse(code: Int) = PostOrderResponse(code, message)
}
