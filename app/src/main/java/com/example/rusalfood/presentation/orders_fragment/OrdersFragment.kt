package com.example.rusalfood.presentation.orders_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R

import com.example.rusalfood.databinding.FragmentOrdersBinding
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent

class OrdersFragment : Fragment(R.layout.fragment_orders), OrdersAdapter.OnItemClickListener {

    private val binding by viewBinding(FragmentOrdersBinding::bind)
    private lateinit var ordersAdapter: OrdersAdapter

    private val ordersViewModel: OrdersViewModel by viewModels { requireContext().appComponent.ordersViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserving()
    }

    private fun initObserving() {
        ordersViewModel.listOrders.observe(viewLifecycleOwner) {
            ordersAdapter.setData(it)
        }
    }

    private fun initRecyclerView() {
        ordersAdapter = OrdersAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.ordersRecyclerView.layoutManager = layoutManager
        binding.ordersRecyclerView.adapter = ordersAdapter
    }

    override fun onItemClick(position: Int, orderId: Int, orderAddress: String) {
        findNavController().navigate(
            OrdersFragmentDirections.actionOrdersFragmentToOrderDetailsFragment(orderId, orderAddress)
        )
    }

}