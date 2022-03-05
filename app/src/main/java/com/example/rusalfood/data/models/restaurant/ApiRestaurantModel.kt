package com.example.rusalfood.data.models.restaurant

import com.example.rusalfood.domain.models.Place

data class ApiRestaurantModel(
    val restaurant: Restaurant
) {
    fun mapToPlace() = Place(
        id = restaurant.restaurant_data.id,
        name = restaurant.restaurant_data.name,
        address = restaurant.restaurant_data.location,
        mainImage = restaurant.image.imageUrl,
        gallery = restaurant.restaurant_data.restaurantImages.map { it.imageUrl }
    )
}