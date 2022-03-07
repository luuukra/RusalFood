package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.GetOrdersByUserIdUseCase
import javax.inject.Inject

class OrdersViewModelFactory @Inject constructor(
    val getOrdersByUserIdUseCase: GetOrdersByUserIdUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(getOrdersByUserIdUseCase) as T
    }
}