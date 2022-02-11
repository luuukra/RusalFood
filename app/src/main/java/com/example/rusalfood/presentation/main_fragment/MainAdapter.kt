package com.example.rusalfood.presentation.main_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.databinding.ItemPlaceBinding
import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.domain.models.Place

class MainAdapter(private val listener: onItemClickListener) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var places: List<Place> = listOf(Place(
        1,
        "(Help!)",
        "Abay Qoshesy, 120",
        "https://images.unsplash.com/photo-1603915102051-9a2a5d90b79a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
        listOf("https://images.unsplash.com/photo-1603915102051-9a2a5d90b79a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"),
        listOf(Food(
            "asd",
            "asd",
            123,
            "asd",
            "https://images.unsplash.com/photo-1603915102051-9a2a5d90b79a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
        ))
    ))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaceBinding.inflate(layoutinflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currPlace = places[position]
        holder.binding.apply {
            tvName.text = currPlace.name
            tvAddress.text = currPlace.address
            Glide.with(ivPhoto.context)
                .load(currPlace.mainImage)
                .into(ivPhoto)
        }
    }

    override fun getItemCount(): Int {
        return places.size
    }


    inner class MainViewHolder(val binding : ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val positionn: Int = bindingAdapterPosition
            if (positionn != RecyclerView.NO_POSITION) {
                listener.onItemClick(positionn, places[positionn].name, places[positionn].id)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(positionn: Int, placeName: String, placeId: Int)
    }
}