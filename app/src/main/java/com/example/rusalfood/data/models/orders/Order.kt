package com.example.rusalfood.data.models.orders

import com.example.rusalfood.domain.models.OrderMine

data class Order(
    val created_at: String,
    val id: Int,
    val order_details: List<OrderDetail>,
    val order_status: Int,
    val restaurant: Restaurant,
    val restaurant_id: Int,
    val total: Int,
    val updated_at: String,
    val user: User,
    val user_id: Int
) {
    fun mapToOrderMine() = OrderMine(
        orderId = id,
        orderAddress = restaurant.location,
        orderStatus = "STATUS",
        totalPrice = total,
        orderDate = "DELETE",
        restaurantName = restaurant.name
    )
}