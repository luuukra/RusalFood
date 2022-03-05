package com.example.rusalfood.presentation.place_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.rusalfood.R
import com.example.rusalfood.databinding.ItemRecyclerviewFoodItemBinding
import com.example.rusalfood.databinding.ItemRecyclerviewFoodItemCategoryBinding
import com.example.rusalfood.domain.models.Food


class PlaceFoodListAdapter(private val placeViewModel: PlaceViewModel, private val context: Context) :
    RecyclerView.Adapter<PlaceFoodListAdapter.PlaceFoodListHolder>() {

    private val foodList = mutableListOf<Food>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceFoodListHolder {
        return when (viewType) {
            R.layout.item_recyclerview_food_item_category -> PlaceFoodListHolder.PlaceFoodCategoriesHolder(
                ItemRecyclerviewFoodItemCategoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_recyclerview_food_item -> PlaceFoodListHolder.PlaceFoodItemsHolder(
                ItemRecyclerviewFoodItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), placeViewModel = placeViewModel
            )
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: PlaceFoodListHolder, position: Int) {
        when (holder) {
            is PlaceFoodListHolder.PlaceFoodCategoriesHolder -> holder.bind(foodList[position] as Food.FoodCategory)
            is PlaceFoodListHolder.PlaceFoodItemsHolder -> holder.bind(foodList[position] as Food.FoodItem)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (foodList[position]) {
            is Food.FoodCategory -> R.layout.item_recyclerview_food_item_category
            else -> R.layout.item_recyclerview_food_item
        }
    }

    fun setData(newFoodList: List<Food>){
        foodList.run {
            clear()
            addAll(newFoodList)
        }
    }

    /*fun setData(newFoodCategoryList: List<FoodCategory>){
        val diffUtils = FoodCatDiffUtils(foodCategoryList, newFoodCategoryList)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        foodCategoryList = newFoodCategoryList
        diffResult.dispatchUpdatesTo(this)
    }*/

    sealed class PlaceFoodListHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        class PlaceFoodCategoriesHolder(private val binding: ItemRecyclerviewFoodItemCategoryBinding) :
            PlaceFoodListHolder(binding) {
            fun bind(foodCategory: Food.FoodCategory) {
                binding.run {
                    categoryNameTv.text = foodCategory.categoryName
                }
            }
        }

        class PlaceFoodItemsHolder(
            private val binding: ItemRecyclerviewFoodItemBinding,
            private val placeViewModel: PlaceViewModel
            ) :
            PlaceFoodListHolder(binding) {
            fun bind(foodItem: Food.FoodItem) {
                binding.run {
                    foodName.text = foodItem.foodName
                    if (foodItem.foodDesc.isNullOrEmpty()) foodDescription.visibility = View.GONE
                    else foodDescription.text = foodItem.foodDesc
                    foodPrice.text = foodItem.foodPrice.toString()
                    if(foodItem.foodImage.isNullOrEmpty()) cardView.visibility = View.GONE
                    else Glide.with(foodImage.context)
                        .load(foodItem.foodImage)
                        .into(foodImage)

                    placeViewModel.listOfFoodWithCategories.value!![bindingAdapterPosition].run {
                        if (this is Food.FoodItem) {
                            foodTextViewAmount.text = this.foodAmount.toString()
                        }
                    }

                    foodButtonPlus.setOnClickListener {
                        placeViewModel.amountIncreaseInPlace(bindingAdapterPosition)
                        bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
                    }

                    foodButtonMinus.setOnClickListener {
                        placeViewModel.amountDecreaseInPlace(bindingAdapterPosition)
                        bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
                    }
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(textView: TextView)
    }

}
