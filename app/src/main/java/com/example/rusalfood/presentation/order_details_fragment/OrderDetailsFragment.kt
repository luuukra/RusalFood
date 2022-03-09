package com.example.rusalfood.presentation.order_details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R
import com.example.rusalfood.databinding.FragmentOrderDetailsBinding
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.presentation.orders_fragment.OrdersViewModel

class OrderDetailsFragment : Fragment(R.layout.fragment_order_details) {

    private val binding by viewBinding(FragmentOrderDetailsBinding::bind)

    private val args: OrderDetailsFragmentArgs by navArgs()

    private val ordersViewModel: OrdersViewModel by viewModels { requireContext().appComponent.ordersViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCurrentDetails()
        initObserve()
    }

    private fun initObserve() {
        ordersViewModel.currentOrder.observe(viewLifecycleOwner) {
            binding.run {
                detailsPlace.text = it.restaurantName
                detailsAddress.text = args.orderAddress
                detailsStatus.text = it.orderStatus
                detailsDate.text = it.orderDate
                detailsNumber.text = it.orderId.toString()
            }
        }
    }

    private fun initCurrentDetails() {
        val order = args.orderId
        ordersViewModel.getOrderById(order)
    }
}