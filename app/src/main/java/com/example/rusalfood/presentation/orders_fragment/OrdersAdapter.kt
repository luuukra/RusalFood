package com.example.rusalfood.presentation.orders_fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rusalfood.data.models.MockOrder
import com.example.rusalfood.databinding.ItemRecyclerviewOrderBinding

class OrdersAdapter (private val listener: OnItemClickListener) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    fun setData(data: List<MockOrder>) {
        diffUtilOrders.submitList(data)
    }

    //Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewOrderBinding.inflate(layoutInflater, parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        var currOrder = diffUtilOrders.currentList[position]
        holder.binding.apply {
            ordersPlaceName.text = currOrder.restaurantName
            ordersPrice.text = currOrder.totalPrice.toString()
            ordersStatus.text = currOrder.orderStatus
            ordersDate.text = currOrder.orderDate

            when (ordersStatus.text) {
                "Done" -> {
                    ordersStatus.setTextColor(Color.parseColor("#008000"))
                }
                "Pending" -> {
                    ordersStatus.setTextColor(Color.parseColor("#088F8F"))
                }
                "In kitchen" -> {
                    ordersStatus.setTextColor(Color.parseColor("#FFC300"))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return diffUtilOrders.currentList.size
    }


    // View Holder + On Item Clicker
    inner class OrdersViewHolder(val binding : ItemRecyclerviewOrderBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(
                    position,
                    diffUtilOrders.currentList[position].orderId,
                    diffUtilOrders.currentList[position].orderAddress,
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, orderId: Int, orderAddress: String)
    }


    // Diff Utils
    private val differCallback = object : DiffUtil.ItemCallback<MockOrder>() {

        override fun areItemsTheSame(oldItem: MockOrder, newItem: MockOrder): Boolean {
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: MockOrder, newItem: MockOrder): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtilOrders = AsyncListDiffer(this, differCallback)
}