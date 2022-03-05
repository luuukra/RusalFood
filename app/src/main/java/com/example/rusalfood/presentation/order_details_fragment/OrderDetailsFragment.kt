package com.example.rusalfood.presentation.order_details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rusalfood.databinding.FragmentOrderDetailsBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.presentation.orders_fragment.OrdersViewModel

class OrderDetailsFragment : Fragment() {

    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: OrderDetailsFragmentArgs by navArgs()

    private val ordersViewModel: OrdersViewModel by viewModels { requireContext().appComponent.ordersViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCurrentDetails()
        setupObserve()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserve() {
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

    private fun setupCurrentDetails() {
        val order = args.orderId
        ordersViewModel.getOrderById(order)
    }
}