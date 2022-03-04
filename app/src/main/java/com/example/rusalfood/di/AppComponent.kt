package com.example.rusalfood.di

import com.example.rusalfood.data.network.RetrofitService
import com.example.rusalfood.presentation.main_fragment.MainViewModelFactory
import com.example.rusalfood.presentation.orders_fragment.OrdersViewModelFactory
import com.example.rusalfood.presentation.place_fragment.PlaceViewModelFactory
import com.example.rusalfood.presentation.sign_in_fragment.SignInViewModelFactory
import com.example.rusalfood.presentation.sign_up_fragment.SignUpViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [VMModule::class, UseCasesModule::class, RetrofitModule::class])
interface AppComponent {

    fun signInViewModelFactory(): SignInViewModelFactory

    fun signUpViewModelFactory(): SignUpViewModelFactory

    fun mainViewModelFactory(): MainViewModelFactory

    fun placeViewModelFactory(): PlaceViewModelFactory

    fun ordersViewModelFactory(): OrdersViewModelFactory

    fun retrofitService(): RetrofitService
}