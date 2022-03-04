package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.GetOrderByIdUseCase
import com.example.rusalfood.domain.usecases.GetOrdersListUseCase
import javax.inject.Inject

class OrdersViewModelFactory @Inject constructor(
    val getOrdersListUseCase: GetOrdersListUseCase,
    val getOrderByIdUseCase: GetOrderByIdUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(getOrdersListUseCase, getOrderByIdUseCase) as T
    }
}