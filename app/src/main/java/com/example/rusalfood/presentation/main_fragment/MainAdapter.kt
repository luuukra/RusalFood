package com.example.rusalfood.presentation.main_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rusalfood.databinding.ItemPlaceBinding
import com.example.rusalfood.domain.models.Place

class MainAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var places: List<Place> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaceBinding.inflate(layoutInflater, parent, false)
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


    inner class MainViewHolder(
        val binding: ItemPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, places[position].name, places[position].id)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, placeName: String, placeId: Int)
    }
}