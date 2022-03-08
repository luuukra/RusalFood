package com.example.rusalfood.presentation.orders_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rusalfood.databinding.FragmentOrdersBinding
import com.example.rusalfood.di.appComponent

class OrdersFragment : Fragment(), OrdersAdapter.OnItemClickListener {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var ordersAdapter: OrdersAdapter

    private val ordersViewModel: OrdersViewModel by viewModels { requireContext().appComponent.ordersViewModelFactory() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserving()
    }

    private fun initObserving() {
        ordersViewModel.listOrders.observe(viewLifecycleOwner) {
            ordersAdapter.setData(it)
        }

        ordersViewModel.navDirection.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }
    }

    private fun initRecyclerView() {
        ordersAdapter = OrdersAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.ordersRecyclerView.layoutManager = layoutManager
        binding.ordersRecyclerView.adapter = ordersAdapter
    }

    override fun onItemClick(position: Int, orderId: Int, orderAddress: String) {
        ordersViewModel.navToOrderDetailsFragment(orderId, orderAddress)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}