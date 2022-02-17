package com.example.rusalfood.presentation.main_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.usecases.DisplayAllPlacesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val displayAllPlacesUseCase: DisplayAllPlacesUseCase,
) : ViewModel() {

    init {
        displayAllPlaces()
    }

    private val _listPlaces = MutableLiveData<List<Place>>()
    val listPlaces: MutableLiveData<List<Place>> = _listPlaces

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized = _isAuthorized

    private fun displayAllPlaces() = viewModelScope.launch(Dispatchers.IO) {
        val places = displayAllPlacesUseCase.execute()
        listPlaces.postValue(places)
    }
}