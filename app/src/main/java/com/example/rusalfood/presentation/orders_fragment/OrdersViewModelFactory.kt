package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.GetOrdersUseCase
import javax.inject.Inject

class OrdersViewModelFactory @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(getOrdersUseCase) as T
    }
}