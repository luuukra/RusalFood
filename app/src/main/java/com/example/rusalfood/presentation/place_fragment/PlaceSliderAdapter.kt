package com.example.rusalfood.presentation.place_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rusalfood.databinding.ItemViewpagerGalleryBinding

class PlaceSliderAdapter(
) : RecyclerView.Adapter<PlaceSliderAdapter.ViewPagerViewHolder>() {

    fun setData(data: List<String>){
        gallery.apply {
            clear()
            addAll(data)
        }
    }

    private val gallery = mutableListOf<String>()

    inner class ViewPagerViewHolder(val binding: ItemViewpagerGalleryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)
        val binding = ItemViewpagerGalleryBinding.inflate(layoutinflater, parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = gallery[position]
        holder.binding.run {
            Glide.with(ivImage.context)
                .load(currentImage)
                .centerCrop()
                .into(ivImage)
        }
    }

    override fun getItemCount(): Int {
        return gallery.size
    }
}