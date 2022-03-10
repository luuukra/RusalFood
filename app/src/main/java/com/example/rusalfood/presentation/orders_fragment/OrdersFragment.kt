package com.example.rusalfood.presentation.orders_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R

import com.example.rusalfood.databinding.FragmentOrdersBinding
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.PreparedOrder
import com.example.rusalfood.domain.shared_pref.EncryptedSharedPrefImpl
import com.example.rusalfood.presentation.main_fragment.MainViewModel

class OrdersFragment : Fragment(R.layout.fragment_orders), OrdersAdapter.OnItemClickListener {

    private val binding by viewBinding(FragmentOrdersBinding::bind)
    private lateinit var ordersAdapter: OrdersAdapter

    private val ordersViewModel: OrdersViewModel by activityViewModels { requireContext().appComponent.ordersViewModelFactory() }
    private val mainViewModel: MainViewModel by activityViewModels { requireContext().appComponent.mainViewModelFactory() }
    private lateinit var sharedPref: EncryptedSharedPrefImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = EncryptedSharedPrefImpl(requireContext())
        initRecyclerView()
        initObserving()
        initViews()
        initComeBack()
    }

    private fun initComeBack() {
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(
                OrdersFragmentDirections.actionOrdersFragmentToSignInFragment()
            )
        }
    }

    private fun initViews() {
        val status = mainViewModel.isAuthorized.value
        if (status != true) {
            binding.signInRequirement.visibility = View.VISIBLE
            binding.ordersRecyclerView.visibility = View.GONE
        } else {
            val token = sharedPref.getString("token", "hz")

            val authString = "Bearer $token"
            ordersViewModel.displayOrders(authString)
        }
    }

    private fun initObserving() {
        ordersViewModel.ordersList.observe(viewLifecycleOwner) {
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