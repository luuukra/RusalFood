package com.example.rusalfood.data.models.orders


import com.example.rusalfood.domain.models.OrderMine
import com.example.rusalfood.domain.models.PreparedProduct
import com.google.gson.annotations.SerializedName

data class Order(
    val `data`: Data,
    val message: String
) {
    fun mapToOrderMine() = OrderMine(
        orderId = data.orderId,
        orderAddress = data.restaurant.location,
        restaurantName = data.restaurant.name,
        totalPrice = data.total,
        orderStatus = "Done",
        orderDate = data.restaurant.updatedAt
    )
}