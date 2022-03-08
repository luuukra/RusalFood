package com.example.rusalfood.data.models.foodList

import com.google.gson.annotations.SerializedName

data class ApiFoodListModel(
    @SerializedName("data")
    val data: Data
)