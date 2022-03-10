package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.orders.Order
import com.example.rusalfood.data.models.orders.OrderMessage
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.OrderMine
import com.example.rusalfood.domain.models.PreparedOrder
import retrofit2.Response
import javax.inject.Inject

//interface GetOrdersUseCase {
//    suspend operator fun invoke(): List<OrderMine>
//}
//
//class GetOrdersUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
//    GetOrdersUseCase {
//
//    override suspend operator fun invoke(): List<OrderMine> {
//        return mainRepository.getOrders().body()!!.map { it.mapToOrderMine() }
//    }
//}