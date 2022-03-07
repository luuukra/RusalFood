package com.example.rusalfood.presentation.orders_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rusalfood.databinding.FragmentOrdersBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.presentation.place_fragment.PlaceFragmentArgs

class OrdersFragment : Fragment(), OrdersAdapter.OnItemClickListener {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var ordersAdapter: OrdersAdapter


    private val ordersViewModel: OrdersViewModel by viewModels { requireContext().appComponent.ordersViewModelFactory() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        initRecyclerView()
        initObserving()

        binding.btnSignIn.setOnClickListener {
            Toast.makeText(requireContext(), "ASD", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initViews() {
        val asd = 1
        if (asd == 1) {
            binding.signInRequirement.visibility = View.VISIBLE
        }
    }

    private fun initObserving() {
        ordersViewModel.listOrders.observe(viewLifecycleOwner) {
            ordersAdapter.orders = it
            ordersAdapter.notifyDataSetChanged()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}