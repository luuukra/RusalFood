package com.example.rusalfood.presentation.main_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.models.Resource
import com.example.rusalfood.domain.usecases.GetAllPlacesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllPlacesUseCase: GetAllPlacesUseCase,
) : ViewModel() {

    init {
        displayAllPlaces()
    }

    private val _placesList: MutableLiveData<Resource<List<Place>>> = MutableLiveData()
    val placesList: LiveData<Resource<List<Place>>> = _placesList

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized = _isAuthorized

    private fun displayAllPlaces() = viewModelScope.launch(Dispatchers.IO) {
        val allPlaces = getAllPlacesUseCase()
        _placesList.postValue(allPlaces)
    }

    fun getClickedPlace(placeId: Int): Place {
        var place =
            placesList.value?.data?.single() {
                it.id == placeId
            } as Place
        return place
    }
}