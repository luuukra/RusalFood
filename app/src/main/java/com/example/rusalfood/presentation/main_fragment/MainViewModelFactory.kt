package com.example.rusalfood.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.data.repositories.MainRepositoryImpl
import com.example.rusalfood.domain.usecases.DisplayAllPlacesUseCase

class MainViewModelFactory(private val mockData : MockData) : ViewModelProvider.Factory {

    private val mainRepository = MainRepositoryImpl(mockData)
    private val displayAllPlacesUseCase = DisplayAllPlacesUseCase(mainRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(displayAllPlacesUseCase) as T
    }

}