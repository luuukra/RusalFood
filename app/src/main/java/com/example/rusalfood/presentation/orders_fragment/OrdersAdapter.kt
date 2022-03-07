package com.example.rusalfood.presentation.orders_fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rusalfood.data.models.orders.mock.MockOrder
import com.example.rusalfood.databinding.ItemRecyclerviewOrderBinding
import com.example.rusalfood.domain.models.OrderMine

class OrdersAdapter (private val listener: OnItemClickListener) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    var orders: List<OrderMine> = emptyList()

//
//    fun setData(data: List<OrderMine>) {
//        diffUtilOrders.submitList(data)
//    }

    //Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewOrderBinding.inflate(layoutInflater, parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        var currOrder = orders[position]
        holder.bind(currOrder)
    }

    override fun getItemCount(): Int {
        return orders.size
    }


    // View Holder + On Item Clicker
    inner class OrdersViewHolder(val binding : ItemRecyclerviewOrderBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(currOrder: OrderMine) {
            binding.run {
                ordersPlaceName.text = currOrder.restaurantName
                ordersPrice.text = currOrder.totalPrice.toString()
                ordersStatus.text = currOrder.orderStatus
                ordersDate.text = currOrder.orderDate

//                when (ordersStatus.text) {
//                    "Done" -> {
//                        ordersStatus.setTextColor(Color.parseColor("#008000"))
//                    }
//                    "Pending" -> {
//                        ordersStatus.setTextColor(Color.parseColor("#088F8F"))
//                    }
//                    "In kitchen" -> {
//                        ordersStatus.setTextColor(Color.parseColor("#FFC300"))
//                    }
//                }
            }
        }

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(
                    position,
                    orders[position].orderId,
                    orders[position].orderAddress,
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, orderId: Int, orderAddress: String)
    }


//    // Diff Utils
//    private val differCallback = object : DiffUtil.ItemCallback<OrderMine>() {
//
//        override fun areItemsTheSame(oldItem: OrderMine, newItem: OrderMine): Boolean {
//            return oldItem.orderId == newItem.orderId
//        }
//
//        override fun areContentsTheSame(oldItem: OrderMine, newItem: OrderMine): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val diffUtilOrders = AsyncListDiffer(this, differCallback)
}