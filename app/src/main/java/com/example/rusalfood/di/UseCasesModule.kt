package com.example.rusalfood.di

import com.example.rusalfood.data.network.IMockOrders
import com.example.rusalfood.data.network.MockOrders
import com.example.rusalfood.domain.usecases.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCasesModule {
    @Singleton
    @Binds
    fun bindGetAllPlacesUseCaseImplToGetAllPlacesUseCase(getAllPlacesUseCaseImpl: GetAllPlacesUseCaseImpl): GetAllPlacesUseCase

    @Singleton
    @Binds
    fun bindSignInUseCaseImplToSignInUseCase(signInUseCaseImpl: SignInUseCaseImpl): SignInUseCase

    @Singleton
    @Binds
    fun bindSignUpUseCaseImplToSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl): SignUpUseCase

    @Singleton
    @Binds
    fun bindGetFoodListUseCaseImplToGetFoodListUseCase(etFoodListUseCaseImpl: GetFoodListUseCaseImpl): GetFoodListUseCase

    @Singleton
    @Binds
    fun bindGetOrdersByUserIdUseCaseImplToGetOrdersByUserIdUseCase(getOrdersByUserIdUseCaseImpl: GetOrdersByUserIdUseCaseImpl) : GetOrdersByUserIdUseCase



//    @Singleton
//    @Binds
//    fun bindMockOrdersToIMockOrders(mockOrders: MockOrders) : IMockOrders

}