package com.example.rusalfood.domain.usecases

import com.example.rusalfood.data.models.orders.OrderMessage
import com.example.rusalfood.domain.irepositories.MainRepository
import com.example.rusalfood.domain.models.ErrorResponse
import com.example.rusalfood.domain.models.PreparedOrder
import com.example.rusalfood.domain.models.PostOrderResponse
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

interface SendOrdersUseCase {
    suspend operator fun invoke(token: String, preparedOrder: PreparedOrder): PostOrderResponse
}

class SendOrdersUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    SendOrdersUseCase {

    override suspend operator fun invoke(authString: String, preparedOrder: PreparedOrder): PostOrderResponse {
       val response = mainRepository.sendOrders(authString, preparedOrder)

        return if (response.isSuccessful) {
            response.body()!!.mapToSendOrderResponse(response.code())

        } else {
            val errorResponse =
                Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
            PostOrderResponse(response.code(), errorResponse.message)
        }
    }
}
