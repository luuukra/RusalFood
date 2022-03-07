package com.example.rusalfood.presentation.place_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.domain.models.Place
import com.example.rusalfood.domain.usecases.GetFoodListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val getFoodListUseCase: GetFoodListUseCase
) : ViewModel() {

    private val _currentPlace = MutableLiveData<Place>()
    val currentPlace: LiveData<Place> = _currentPlace

    //full list of food by categories for restaurant menu
    private val _listOfFoodWithCategories: MutableLiveData<List<Food>> = MutableLiveData()
    val listOfFoodWithCategories: LiveData<List<Food>> = _listOfFoodWithCategories

    //list of categories for horizontal categories rv
    private val _listOfCategories: MutableLiveData<List<Food.FoodCategory>> = MutableLiveData()
    val listOfCategories: LiveData<List<Food.FoodCategory>> = _listOfCategories

    fun getIntoPlace(place: Place) = viewModelScope.launch(Dispatchers.IO) {
        _currentPlace.postValue(place)
    }

    fun getFoodListById(placeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val foodAndFoodCatList = getFoodListUseCase(placeId)
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
    private val _countedFoodList = MutableLiveData<List<Food.FoodItem>>()
    val countedFoodList: LiveData<List<Food.FoodItem>> = _countedFoodList

    private val _totalAmount = MutableLiveData<Int>()
    val totalAmount: LiveData<Int> = _totalAmount

    private val _totalSum = MutableLiveData<Int>()
    val totalSum: LiveData<Int> = _totalSum

    private fun setAmountSum() {
        _totalAmount.value = _countedFoodList.value?.sumOf { it.foodAmount }
        _totalSum.value = _countedFoodList.value?.sumOf { it.foodAmount * it.foodPrice }
    }

    private fun setCountedList() {
        _countedFoodList.let { list ->
            list.value = _listOfFoodWithCategories.value!!.filterNot { food ->
                food is Food.FoodCategory
            }
                .map { it as Food.FoodItem }
                .filter { it.foodAmount > 0 }
        }
    }


    fun amountIncreaseInPlace(currentPosition: Int) {
        _listOfFoodWithCategories.value!![currentPosition].run {
            if (this is Food.FoodItem) {
                this.foodAmount += 1
                setCountedList()
                setAmountSum()
            }
        }
    }

    fun amountIncreaseInBasket(currentPosition: Int) {
        _countedFoodList.value!![currentPosition].run {
            this.foodAmount += 1
            setCountedList()
            setAmountSum()
        }
    }

    fun amountDecreaseInPlace(currentPosition: Int) {
        _listOfFoodWithCategories.value!![currentPosition].run {
            if (this is Food.FoodItem) {
                if (this.foodAmount <= 0) {
                    this.foodAmount = 0
                    setCountedList()
                    setAmountSum()
                } else {
                    this.foodAmount -= 1
                    setCountedList()
                    setAmountSum()
                }
            }
        }
    }

    fun amountDecreaseInBasket(currentPosition: Int) {
        _countedFoodList.value!![currentPosition].run {
            if (this.foodAmount <= 0) {
                this.foodAmount = 0
                setCountedList()
                setAmountSum()
            } else {
                this.foodAmount -= 1
                setCountedList()
                setAmountSum()
            }
        }
    }

    fun resetAmounts() {
        _listOfFoodWithCategories.value!!.filterNot { it is Food.FoodCategory }
            .map {
                it as Food.FoodItem
            }.forEach {
                it.foodAmount = 0
            }
    }

    fun resetListOfFoodWithCategories() {
        _listOfFoodWithCategories.value = emptyList()
    }

}
