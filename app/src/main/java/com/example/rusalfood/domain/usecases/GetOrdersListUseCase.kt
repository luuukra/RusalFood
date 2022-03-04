package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject

interface GetOrdersListUseCase {
    suspend operator fun invoke() : List<MockOrder>
}

class GetOrdersListUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetOrdersListUseCase {

    override suspend operator fun invoke() : List<MockOrder> {
        return mainRepository.getOrdersList()
    }
}