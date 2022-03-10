package com.example.rusalfood.data.models.orders


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("order_detail")
    val orderDetail: List<OrderDetail>,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("order_status")
    val orderStatus: String,
    val restaurant: Restaurant,
    val total: Int,
    @SerializedName("user_id")
    val userId: Int
)