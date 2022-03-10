package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.data.models.orders.Order
import com.example.rusalfood.domain.models.DomainOrder
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.usecases.GetOrdersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {


    private val _currentOrder = MutableLiveData<MockOrder>()
    val currentOrder: LiveData<MockOrder> = _currentOrder


    private val _ordersList: MutableLiveData<List<DomainOrder>> = MutableLiveData()
    val ordersList: LiveData<List<DomainOrder>> = _ordersList

    fun displayOrders(authString: String) = viewModelScope.launch(Dispatchers.IO) {
        val orders = getOrdersUseCase(authString)
        _ordersList.postValue(orders)
    }


    fun getClickedOrder(orderId: Int): DomainOrder {
        var order =
            ordersList.value?.single() {
                it.orderId == orderId
            } as DomainOrder
        return order
    }
}