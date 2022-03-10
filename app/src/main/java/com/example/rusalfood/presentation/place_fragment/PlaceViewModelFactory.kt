package com.example.rusalfood.presentation.place_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.data.repositories.LoginRepositoryImpl
import com.example.rusalfood.domain.irepositories.LoginRepository
import com.example.rusalfood.domain.usecases.GetFoodListUseCase
import com.example.rusalfood.domain.usecases.SendOrdersUseCase
import javax.inject.Inject

class PlaceViewModelFactory @Inject constructor(
    private val getFoodListUseCase: GetFoodListUseCase,
    private val sendOrdersUseCase: SendOrdersUseCase,
    private val loginRepository: LoginRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaceViewModel(getFoodListUseCase, sendOrdersUseCase, loginRepository) as T
    }
}