//package com.example.rusalfood.domain.usecases
//
//import com.example.rusalfood.data.models.orders.mock.MockOrder
//import com.example.rusalfood.domain.irepositories.MainRepository
//import javax.inject.Inject
//
//interface GetOrderDetailsUseCase {
//    suspend operator fun invoke(orderId: Int) : MockOrder
//}
//
//class GetOrderDetailsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
//    GetOrderDetailsUseCase {
//
//    override suspend operator fun invoke(orderId: Int) : MockOrder {
//        return mainRepository.getOrderDetails(orderId)
//    }
//}