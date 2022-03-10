package com.example.rusalfood.presentation.order_details_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R
import com.example.rusalfood.databinding.FragmentOrderDetailsBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.presentation.orders_fragment.OrdersViewModel

class OrderDetailsFragment : Fragment(R.layout.fragment_order_details) {

    private val binding by viewBinding(FragmentOrderDetailsBinding::bind)

    private val args: OrderDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCurrentOrder()
    }

    private fun initCurrentOrder() {
        val order = args.order
            binding.run {
                detailsPlace.text = order.restaurantName
                detailsAddress.text = order.orderAddress
                detailsStatus.text = order.orderStatus
                detailsNumber.text = order.orderId.toString()
                totalPrice.text = order.totalPrice.toString()
            }
        }
    }

