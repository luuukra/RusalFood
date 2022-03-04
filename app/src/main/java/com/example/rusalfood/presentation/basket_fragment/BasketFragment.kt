package com.example.rusalfood.presentation.basket_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rusalfood.databinding.FragmentBasketBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.presentation.place_fragment.PlaceViewModel

class BasketFragment: Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private val args: BasketFragmentArgs by navArgs()
    private lateinit var basketAdapter: BasketAdapter

    private val placeViewModel: PlaceViewModel by activityViewModels { requireContext().appComponent.placeViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setupRecyclerView()
        setupNameAndAddress()
        launchObserving()
        setTotalCounter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setTotalCounter() {
        placeViewModel.totalSum.observe(viewLifecycleOwner) {
            binding.textViewSum.text = it.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun launchObserving() {
        placeViewModel.countedFoodList.observe(viewLifecycleOwner) {
            basketAdapter.setData(it)
            basketAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        basketAdapter = BasketAdapter(placeViewModel)
        val layoutManagers = LinearLayoutManager(requireContext())
        binding.basketRecyclerView.apply {
            layoutManager = layoutManagers
            adapter = basketAdapter
        }
    }

    private fun setupNameAndAddress() {
        binding.apply {
            basketPlaceName.text = args.placeName
            basketPlaceAddress.text = args.placeAddress
        }
    }
}