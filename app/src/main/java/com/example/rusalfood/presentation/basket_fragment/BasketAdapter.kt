package com.example.rusalfood.presentation.basket_fragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rusalfood.databinding.ItemRecyclerviewBasketBinding
import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.presentation.place_fragment.PlaceViewModel

class BasketAdapter(private val placeViewModel: PlaceViewModel) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    fun setData(data: List<Food.FoodItem>) {
        basketList.run {
            clear()
            addAll(data)
        }
    }

    private val basketList = mutableListOf<Food.FoodItem>()

    // Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewBasketBinding.inflate(layoutInflater, parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        var currBasketFood = basketList[position]
        holder.binding.run {
            basketFoodName.text = currBasketFood.foodName
            basketFoodPrice.text = currBasketFood.foodPrice.toString()

            basketTextViewAmount.text = placeViewModel.countedFoodList.value?.get(position)?.foodAmount.toString()

            Glide.with(basketImage.context)
                .load(currBasketFood.foodImage)
                .into(basketImage)


            basketButtonPlus.setOnClickListener {
                placeViewModel.amountIncreaseInBasket(position)
                notifyItemChanged(position)
            }

            basketButtonMinus.setOnClickListener {
                placeViewModel.amountDecreaseInBasket(position)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return basketList.size
    }


    // View Holder
    inner class BasketViewHolder(val binding : ItemRecyclerviewBasketBinding) : RecyclerView.ViewHolder(binding.root)
}