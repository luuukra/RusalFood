package com.example.rusalfood.presentation.basket_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R
import com.example.rusalfood.databinding.FragmentBasketBinding
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.PreparedOrder
import com.example.rusalfood.presentation.place_fragment.PlaceViewModel

class  BasketFragment: Fragment(R.layout.fragment_basket) {

    private val binding by viewBinding(FragmentBasketBinding::bind)
    private val args: BasketFragmentArgs by navArgs()
    private lateinit var basketAdapter: BasketAdapter

    private val placeViewModel: PlaceViewModel by activityViewModels { requireContext().appComponent.placeViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initNameAndAddress()
        initObserving()
        initTotalCounter()
        initSendOrderButton()

    }


    private fun initSendOrderButton() {
        binding.payButton.setOnClickListener {
            val token = placeViewModel.getToken().toString()
            val format = "Authorization: Bearer Token "
//            val newOrder = PreparedOrder(
//                args.placeId,
//                placeViewModel.countedFoodList.value!!.map { it.mapToPreparedProduct() }
//            )
//            placeViewModel.sendOrders(token, newOrder)


            Toast.makeText(requireContext(), token, Toast.LENGTH_SHORT).show()

        }
    }


    private fun initTotalCounter() {
        placeViewModel.totalSum.observe(viewLifecycleOwner) {
            binding.textViewSum.text = it.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserving() {
        placeViewModel.countedFoodList.observe(viewLifecycleOwner) {
            basketAdapter.setData(it)
            basketAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {
        basketAdapter = BasketAdapter(placeViewModel)
        val layoutManagers = LinearLayoutManager(requireContext())
        binding.basketRecyclerView.apply {
            layoutManager = layoutManagers
            adapter = basketAdapter
        }
    }

    private fun initNameAndAddress() {
        binding.apply {
            basketPlaceName.text = args.placeName
            basketPlaceAddress.text = args.placeAddress
        }
    }
}