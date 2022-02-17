package com.example.rusalfood.presentation.place_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.GetIntoPlaceUseCase
import javax.inject.Inject

class PlaceViewModelFactory @Inject constructor(
    val getIntoPlaceUseCase: GetIntoPlaceUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaceViewModel(getIntoPlaceUseCase) as T
    }
}