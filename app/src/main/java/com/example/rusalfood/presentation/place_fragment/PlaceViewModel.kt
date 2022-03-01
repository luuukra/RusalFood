package com.example.rusalfood.presentation.place_fragment

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
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
    val listOfFoodWithCategories: LiveData<List<Food>> = _listOfFoodWithCategories

    //list of categories for horizontal categories rv
    private val _listOfCategories = MutableLiveData<List<Food.FoodCategory>>()
    val listOfCategories: LiveData<List<Food.FoodCategory>> = _listOfCategories

    fun getIntoPlace(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val place = getIntoPlaceUseCase.execute(placeId)
        currentPlace.postValue(place)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getFoodListById(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        var foodAndFoodCatList = getFoodListUseCase.getFoodAndCategoriesList(placeId)
        _listOfFoodWithCategories.postValue(foodAndFoodCatList.first)
        _listOfCategories.postValue(foodAndFoodCatList.second)
    }

    //list of indexes in full list of food and categories for TabbedListMediator
    fun getCategoriesIndexesList(): List<Int> {
        return listOfFoodWithCategories.value!!.withIndex().filterNot {
            it.value is Food.FoodItem
        }.map { it.index }
    }

    // Basket Clicks & Food Amounts
    var countedFoodList = MutableLiveData<List<Food.FoodItem>>()

    var totalAmount = MutableLiveData<Int>()

    fun setTotalAmount() {


    }



    fun setCountedList() {
        countedFoodList.value =
            _listOfFoodWithCategories.value!!.filterNot { it is Food.FoodCategory }
                .map { it as Food.FoodItem }
                .filter { it.foodAmount > 0 }
    }

    fun amountIncrease(currentPosition: Int) {
        _listOfFoodWithCategories.value!![currentPosition].apply {
            if (this is Food.FoodItem) {
                this.foodAmount += 1
                setCountedList()
            }
        }
    }

    fun amountIncreaseInb(currentPosition: Int) {
        countedFoodList.value!![currentPosition].apply {
                this.foodAmount += 1
                setCountedList()
        }
    }

    fun amountDecrease(currentPosition: Int) {
        _listOfFoodWithCategories.value!![currentPosition].apply {
            if (this is Food.FoodItem) {
                if (this.foodAmount <= 0) {
                    this.foodAmount = 0
                    setCountedList()
                } else {
                    this.foodAmount -= 1
                    setCountedList()
                }
            }
        }
    }

    fun amountDecreaseInb(currentPosition: Int) {
        countedFoodList.value!![currentPosition].apply {
                if (this.foodAmount <= 0) {
                    this.foodAmount = 0
                    setCountedList()
                } else {
                    this.foodAmount -= 1
                    setCountedList()
                }
        }
    }

}
