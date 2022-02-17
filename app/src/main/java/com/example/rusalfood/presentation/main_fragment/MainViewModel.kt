package com.example.rusalfood.presentation.main_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.models.Resource
import com.example.rusalfood.domain.usecases.DisplayAllPlacesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val displayAllPlacesUseCase: DisplayAllPlacesUseCase,
) : ViewModel() {

    init {
        displayAllPlaces()
    }

    private val _listPlaces = MutableLiveData<Resource<List<Place>>>()
    val listPlaces: MutableLiveData<Resource<List<Place>>> = _listPlaces

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized = _isAuthorized

    private fun displayAllPlaces() = viewModelScope.launch(Dispatchers.IO) {
        listPlaces.postValue(Resource.Loading())
        delay(2000) // skeletons ;)
        val places = displayAllPlacesUseCase.execute()
        listPlaces.postValue(Resource.Success(places))
    }


}