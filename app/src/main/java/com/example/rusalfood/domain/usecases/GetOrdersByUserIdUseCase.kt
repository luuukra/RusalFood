package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.orders.Order
import com.example.rusalfood.domain.irepositories.MainRepository
import retrofit2.Response
import javax.inject.Inject

interface GetOrdersByUserIdUseCase {
    suspend operator fun invoke(userId: Int) : Response<List<Order>>
}

class GetOrdersByUserIdUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetOrdersByUserIdUseCase {

    override suspend operator fun invoke(userId: Int) : Response<List<Order>> {
        return mainRepository.getOrdersByUserId(userId)
    }
}