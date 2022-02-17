package com.example.rusalfood.presentation.place_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.usecases.GetIntoPlaceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val getIntoPlaceUseCase: GetIntoPlaceUseCase
) : ViewModel() {

    private val _currentPlace = MutableLiveData<Place>()
    val currentPlace: MutableLiveData<Place> = _currentPlace

    fun getIntoPlace(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val place = getIntoPlaceUseCase.execute(placeId)
        currentPlace.postValue(place)
    }
}