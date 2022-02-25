package com.example.rusalfood.presentation.basket_fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rusalfood.databinding.ItemRecyclerviewBasketBinding
import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.presentation.place_fragment.PlaceViewModel

class BasketAdapter(private val placeViewModel: PlaceViewModel) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    var basketList: List<Food.FoodItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    // Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewBasketBinding.inflate(layoutInflater, parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        var currBasketFood = basketList[position]
        holder.binding.apply {
            basketFoodName.text = currBasketFood.foodName
            basketFoodPrice.text = currBasketFood.foodPrice.toString()
            basketTextViewAmount.text = placeViewModel.countedFoodList.value?.get(position)?.foodAmount.toString()
            Glide.with(basketImage.context)
                .load(currBasketFood.foodImage)
                .into(basketImage)

            // TO FIX..

            basketButtonPlus.setOnClickListener {
                placeViewModel.amountIncrease(position + 1)
                notifyItemChanged(position + 1)
            }

            basketButtonMinus.setOnClickListener {
                placeViewModel.amountDecrease(position + 1)
                notifyItemChanged(position + 1)
            }
        }
    }

    override fun getItemCount(): Int {
        return basketList.size
    }


    // View Holder
    inner class BasketViewHolder(val binding : ItemRecyclerviewBasketBinding) : RecyclerView.ViewHolder(binding.root)
}