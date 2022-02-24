package com.example.rusalfood.presentation.place_fragment

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rusalfood.databinding.ItemRecyclerviewCategoryBinding
import com.example.rusalfood.domain.models.Food.FoodCategory


class PlaceCategoriesAdapter :
    RecyclerView.Adapter<PlaceCategoriesAdapter.PlaceCtgViewHolder>() {

    var foodCategoryList = emptyList<FoodCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceCtgViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewCategoryBinding.inflate(layoutInflater, parent, false)
        return PlaceCtgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceCtgViewHolder, position: Int) {
        var currCategory = foodCategoryList[position]
        holder.binding.apply {
            Log.d("<<<<<<tag", "BINDING" )
            horizontalCategoriesTextView.text = currCategory.categoryName
            if (currCategory.isChecked) setCheckedBckgrndVisibility(holder, View.VISIBLE)
            else setCheckedBckgrndVisibility(holder, View.INVISIBLE)
        }
    }

    private fun setCheckedBckgrndVisibility(holder: PlaceCtgViewHolder, visibility: Int) {
        holder.binding.selectedOverlay.visibility = visibility
    }

    override fun getItemCount(): Int {
        return foodCategoryList.size
    }

    /*fun setData(newFoodCategoryList: List<FoodCategory>){
        val diffUtils = FoodCatDiffUtils(foodCategoryList, newFoodCategoryList)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        foodCategoryList = newFoodCategoryList
        diffResult.dispatchUpdatesTo(this)
    }*/

    inner class PlaceCtgViewHolder(val binding: ItemRecyclerviewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            //notifyItemChanged(checkedItemPosition)
            foodCategoryList.forEach() { it.isChecked = false }
            val currentPosition = bindingAdapterPosition
            foodCategoryList[currentPosition].isChecked = true
            notifyDataSetChanged()
            //checkedItemPosition = position
            //notifyItemChanged(currentPosition)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(textView: TextView)
    }

    //FoodCategories DiffUtils
   /* private class FoodCatDiffUtils(private val oldList: List<FoodCategory>, private val newList: List<FoodCategory>)
        : DiffUtil.Callback() {

        *//*override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem == newItem
        }*//*

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].categoryName == newList[newItemPosition].categoryName
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].isChecked == newList[newItemPosition].isChecked
        }

        //val diffUtilFoodCategories = AsyncListDiffer(this, differCallback)
    }*/

}
