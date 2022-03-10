package com.example.rusalfood.domain.usecases

import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.DomainOrder
import javax.inject.Inject

interface GetOrdersUseCase {
    suspend operator fun invoke(authString: String): List<DomainOrder>
}

class GetOrdersUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetOrdersUseCase {

    override suspend operator fun invoke(authString: String): List<DomainOrder> {
        val listOfOrders = mainRepository.getOrders(authString).body()?.data?.order
        return listOfOrders!!.map { it.mapToDomainOrder() }
    }
}
