package com.example.rusalfood.data.models.orders

import com.example.rusalfood.domain.models.DomainOrder
import com.example.rusalfood.domain.models.OrderStatus

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
    fun mapToDomainOrder() = DomainOrder(
        orderId = id,
        orderAddress = restaurant.location,
        orderStatus = OrderStatus.values()[order_status].status,
        totalPrice = total,
        restaurantName = restaurant.name
    )
}