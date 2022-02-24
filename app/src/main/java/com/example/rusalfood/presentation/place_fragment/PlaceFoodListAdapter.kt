package com.example.rusalfood.presentation.place_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.rusalfood.R
import com.example.rusalfood.databinding.ItemRecyclerviewFoodItemBinding
import com.example.rusalfood.databinding.ItemRecyclerviewFoodItemCategoryBinding
import com.example.rusalfood.domain.models.Food


class PlaceFoodListAdapter :
    RecyclerView.Adapter<PlaceFoodListAdapter.PlaceFoodListHolder>() {

    var foodList = emptyList<Food>()


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
                )
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
                binding.apply {
                    categoryNameTv.text = foodCategory.categoryName
                }
            }
        }

        class PlaceFoodItemsHolder(private val binding: ItemRecyclerviewFoodItemBinding) :
            PlaceFoodListHolder(binding) {
            fun bind(foodItem: Food.FoodItem) {
                binding.apply {
                    foodName.text = foodItem.foodName
                    foodDescription.text = foodItem.foodDesc
                    foodPrice.text = foodItem.foodPrice.toString()
                    Glide.with(foodImage.context)
                        .load(foodItem.foodImage)
                        .into(foodImage)
                }
            }
        }

    }


    interface OnItemClickListener {
        fun onItemClick(textView: TextView)
    }

}
