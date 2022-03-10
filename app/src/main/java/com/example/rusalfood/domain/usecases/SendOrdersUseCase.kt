package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.orders.OrderMessage
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.PreparedOrder
import retrofit2.Response
import javax.inject.Inject

interface SendOrdersUseCase {
    suspend operator fun invoke(token: String, preparedOrder: PreparedOrder): Response<OrderMessage>
}

class SendOrdersUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    SendOrdersUseCase {

    override suspend operator fun invoke(token: String, preparedOrder: PreparedOrder): Response<OrderMessage> {
        return mainRepository.sendOrders(token, preparedOrder)
    }
}
