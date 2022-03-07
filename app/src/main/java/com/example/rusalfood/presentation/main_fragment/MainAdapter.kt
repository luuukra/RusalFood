package com.example.rusalfood.presentation.main_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.rusalfood.databinding.ItemRecyclerviewPlaceBinding
import com.example.rusalfood.di.RetrofitModule
import com.example.rusalfood.domain.models.Place

class MainAdapter (private val listener: OnItemClickListener) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    fun setData(data: List<Place>) {
        diffUtilPlaces.submitList(data)
    }

    //Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewPlaceBinding.inflate(layoutInflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var currPlace = diffUtilPlaces.currentList[position]
        holder.binding.run {
            tvName.text = currPlace.name
            tvAddress.text = currPlace.address
            Glide.with(ivPhoto.context)
                .load(currPlace.mainImage)
                .into(ivPhoto)
        }
    }

    override fun getItemCount(): Int {
        return diffUtilPlaces.currentList.size
    }


    // View Holder + On Item Clicker
    inner class MainViewHolder(val binding : ItemRecyclerviewPlaceBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(
                    position,
                    diffUtilPlaces.currentList[position].name,
                    diffUtilPlaces.currentList[position].id
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, placeName: String, placeId: Int)
    }


    // Diff Utils
    private val differCallback = object : DiffUtil.ItemCallback<Place>() {

        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtilPlaces = AsyncListDiffer(this, differCallback)
}