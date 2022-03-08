package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.domain.usecases.GetOrderByIdUseCase
import com.example.rusalfood.domain.usecases.GetOrdersListUseCase
import com.example.rusalfood.presentation.place_fragment.PlaceFragmentDirections
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

    private val _currentOrder = MutableLiveData<MockOrder>()
    val currentOrder: LiveData<MockOrder> = _currentOrder

    private val _navDirection: MutableLiveData<NavDirections> = MutableLiveData()
    val navDirection: LiveData<NavDirections> = _navDirection

    private fun displayAllOrders() = viewModelScope.launch(Dispatchers.IO) {
        val orders = getOrdersListUseCase()
        _listOrders.postValue(orders)
    }

    fun getOrderById(orderId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val order = getOrderByIdUseCase(orderId)
        _currentOrder.postValue(order)
    }

    fun navToOrderDetailsFragment(orderId: Int, orderAddress: String) {
        _navDirection.postValue(
            OrdersFragmentDirections.actionOrdersFragmentToOrderDetailsFragment(orderId, orderAddress)
            )
    }

}