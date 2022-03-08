package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.domain.usecases.GetOrderByIdUseCase
import com.example.rusalfood.domain.usecases.GetOrdersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersListUseCase: GetOrdersListUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,

    ) : ViewModel() {

    init {
        displayAllOrders()
    }

    private val _listOrders = MutableLiveData<List<MockOrder>>()
    val listOrders: LiveData<List<MockOrder>> = _listOrders

    private fun displayAllOrders() = viewModelScope.launch(Dispatchers.IO) {
        val orders = getOrdersListUseCase()
        _listOrders.postValue(orders)
    }

    private val _currentOrder = MutableLiveData<MockOrder>()
    val currentOrder: LiveData<MockOrder> = _currentOrder

    fun getOrderById(orderId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val order = getOrderByIdUseCase(orderId)
        _currentOrder.postValue(order)
    }


}