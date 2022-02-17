package com.example.rusalfood.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.data.repositories.MainRepositoryImpl
import com.example.rusalfood.domain.usecases.DisplayAllPlacesUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    val displayAllPlacesUseCase: DisplayAllPlacesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(displayAllPlacesUseCase) as T
    }
}