package com.example.rusalfood.domain.models

import android.os.Parcelable
import com.example.rusalfood.R
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DomainOrder(
    val orderId: Int,
    val orderAddress: String,
    val restaurantName: String,
    val totalPrice: Int,
    val orderStatus: String
) : Serializable

enum class OrderStatus(val status: String) {//todo Locales
    IN_PROGRESS("In Progress"),//1
    IN_THE_KITCHEN("In the kitchen"),//2
    READY("Ready"),//3
    COMPLETED("Completed")//4
}
