package com.example.rusalfood.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.GetAllPlacesUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    val getAllPlacesUseCase: GetAllPlacesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getAllPlacesUseCase) as T
    }
}