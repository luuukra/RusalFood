package com.example.rusalfood.data.models.orders


import com.example.rusalfood.domain.models.OrderMine
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("order_detail")
    val orderDetail: List<OrderDetail>,
    @SerializedName("order_id")
    val orderId: Int,
    val restaurant: Restaurant,
    val total: Int,
    @SerializedName("user_id")
    val userId: Int
) {
    fun mapToOrderMine() = OrderMine(
        orderId = orderId,
        orderAddress = restaurant.location,
        restaurantName = restaurant.name,
        totalPrice = total,
        orderStatus = "Done",
        orderDate = restaurant.createdAt
    )
}