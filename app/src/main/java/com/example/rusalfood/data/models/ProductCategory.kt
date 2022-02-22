package com.example.rusalfood.data.models

data class ProductCategory(
    val product_category_id: Int,
    val product_category_name: String,
    val products: List<Product>
)