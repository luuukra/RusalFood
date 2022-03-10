package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class OrdersViewModelFactory @Inject constructor(

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel() as T
    }
}