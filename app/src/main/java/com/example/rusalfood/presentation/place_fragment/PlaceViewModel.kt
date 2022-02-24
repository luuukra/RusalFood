package com.example.rusalfood.presentation.place_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.usecases.GetFoodListUseCase
import com.example.rusalfood.domain.usecases.GetIntoPlaceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val getIntoPlaceUseCase: GetIntoPlaceUseCase,
    private val getFoodListUseCase: GetFoodListUseCase
) : ViewModel() {

    private val _currentPlace = MutableLiveData<Place>()
    val currentPlace: MutableLiveData<Place> = _currentPlace

    //full list of food by categories for restaurant menu
    private val _listOfFoodWithCategories = MutableLiveData<List<Food>>()
    var listOfFoodWithCategories: MutableLiveData<List<Food>> = _listOfFoodWithCategories

    //list of categories and its' indexes in full list of food and categories
    private val _listOfCategoriesIndexes = MutableLiveData<List<Int>>()
    var listOfCategoriesIndexes: MutableLiveData<List<Int>> =
        _listOfCategoriesIndexes

    //list of categories for horizontal categories rv
    private val _listOfCategories = MutableLiveData<List<Food.FoodCategory>>()
    var listOfCategories: MutableLiveData<List<Food.FoodCategory>> = _listOfCategories

    fun getIntoPlace(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val place = getIntoPlaceUseCase.execute(placeId)
        currentPlace.postValue(place)
    }

    fun getFoodListById(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val foodListResponse = getFoodListUseCase.getFoodList(placeId)
        listOfFoodWithCategories.postValue(
            FoodListResponseConverter.toFoodItemsAndCategoriesList(
                foodListResponse
            )
        )
        listOfCategoriesIndexes.postValue(FoodListResponseConverter.toCategoriesIndexesList())
        listOfCategories.postValue(FoodListResponseConverter.toCategoriesList(foodListResponse))

    }
}
