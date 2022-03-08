package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.domain.irepositories.MainRepository
import javax.inject.Inject

interface GetOrderByIdUseCase {
    suspend operator fun invoke(orderId: Int) : MockOrder
}

class GetOrderByIdUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetOrderByIdUseCase {

    override suspend operator fun invoke(orderId: Int) : MockOrder {
        return mainRepository.getOrderById(orderId)
    }
}