package com.example.rusalfood.presentation.place_fragment

import android.annotation.SuppressLint
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

    @SuppressLint("NullSafeMutableLiveData")
    fun getFoodListById(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val foodAndFoodCatList = getFoodListUseCase.getFoodAndCategoriesList(placeId)
        _listOfFoodWithCategories.postValue(foodAndFoodCatList.first)
        _listOfCategoriesIndexes.postValue(toCategoriesIndexesList(foodAndFoodCatList.first))
        _listOfCategories.postValue(foodAndFoodCatList.second)
    }

    private fun toCategoriesIndexesList(foodItemsAndCategories: List<Food>): List<Int> {
        return foodItemsAndCategories.withIndex().filterNot {
            it.value is Food.FoodItem
        }.map { it.index }
    }
}
