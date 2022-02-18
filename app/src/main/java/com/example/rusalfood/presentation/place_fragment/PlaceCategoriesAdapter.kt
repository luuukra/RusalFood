package com.example.rusalfood.presentation.place_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rusalfood.databinding.ItemRecyclerviewCategoryBinding

class PlaceCategoriesAdapter() : RecyclerView.Adapter<PlaceCategoriesAdapter.PlaceCtgViewHolder>() {

    var categories: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceCtgViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewCategoryBinding.inflate(layoutinflater, parent, false)
        return PlaceCtgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceCtgViewHolder, position: Int) {
        var currPlace = categories[position]
        holder.binding.apply {
            tvCtg.text = currPlace
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class PlaceCtgViewHolder(val binding : ItemRecyclerviewCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}