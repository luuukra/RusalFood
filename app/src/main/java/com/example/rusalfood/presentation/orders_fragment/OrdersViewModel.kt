package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.data.models.orders.mock.MockOrder
import com.example.rusalfood.domain.models.OrderMine
import com.example.rusalfood.domain.usecases.GetOrdersByUserIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersByUserIdUseCase: GetOrdersByUserIdUseCase,
    ) : ViewModel() {

    init {
        displayAllOrders(1)
    }

    private val _listOrders = MutableLiveData<List<OrderMine>>()
    val listOrders: LiveData<List<OrderMine>> = _listOrders

    private fun displayAllOrders(userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val orders = getOrdersByUserIdUseCase(userId)
        if (orders.isSuccessful) {
            _listOrders.postValue(orders.body()!!.map { it.data.mapToOrderMine() })
            }
        }
    }

