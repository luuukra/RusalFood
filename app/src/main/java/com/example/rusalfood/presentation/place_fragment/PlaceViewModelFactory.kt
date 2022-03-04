package com.example.rusalfood.presentation.place_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.GetFoodListUseCase
import javax.inject.Inject

class PlaceViewModelFactory @Inject constructor(
    private val getFoodListUseCase: GetFoodListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaceViewModel(getFoodListUseCase) as T
    }
}