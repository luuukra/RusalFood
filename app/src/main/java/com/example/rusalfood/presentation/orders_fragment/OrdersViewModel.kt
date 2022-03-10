package com.example.rusalfood.presentation.orders_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.domain.models.OrderMine
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.models.Resource
import com.example.rusalfood.domain.usecases.GetOrdersUseCase
import com.example.rusalfood.presentation.place_fragment.PlaceFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {




    private val _ordersList: MutableLiveData<List<OrderMine>> = MutableLiveData()
    val ordersList: LiveData<List<OrderMine>> = _ordersList

    fun displayOrders(authString: String) = viewModelScope.launch(Dispatchers.IO) {
        val orders = getOrdersUseCase(authString)
        _ordersList.postValue(orders)
    }

}