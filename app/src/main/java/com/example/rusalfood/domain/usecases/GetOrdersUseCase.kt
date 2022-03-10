package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.OrderMine
import javax.inject.Inject

interface GetOrdersUseCase {
    suspend operator fun invoke(authString: String): List<OrderMine>
}

class GetOrdersUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetOrdersUseCase {

    override suspend operator fun invoke(authString: String): List<OrderMine> {
        val listOrder = mainRepository.getOrders(authString).body()!!.map {
            it.data.order }.flatten()


        return listOrder.map {
            it.mapToOrderMine()
        }
        }
    }
