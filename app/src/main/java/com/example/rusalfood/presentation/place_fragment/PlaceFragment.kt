package com.example.rusalfood.presentation.place_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.Resource

class PlaceFragment: Fragment() {

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    private val args: PlaceFragmentArgs by navArgs()
    private lateinit var placeViewPagerAdapter: PlaceSliderAdapter
    private lateinit var placeCategoriesAdapter: PlaceCategoriesAdapter

    private val placeViewModel: PlaceViewModel by viewModels { requireContext().appComponent.placeViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupCategoriesRecyclerView()
        setupCurrentPlace()
        launchObserving()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun launchObserving() {
        placeViewModel.currentPlace.observe(viewLifecycleOwner, {
            placeViewPagerAdapter.vpImages = it.gallery // Картинки в слайдере
            binding.placeTvAddress.text = it.address // Адрес заведения
            placeCategoriesAdapter.categories = it.categoryList // Горизонтальный список категорий
            placeViewPagerAdapter.notifyDataSetChanged()
            placeCategoriesAdapter.notifyDataSetChanged()
        })
    }

    private fun setupCurrentPlace() {
        val placeId = args.placeId
        placeViewModel.getIntoPlace(placeId)
    }

    private fun setupViewPager() {
        placeViewPagerAdapter = PlaceSliderAdapter()
        binding.viewPager.adapter = placeViewPagerAdapter
    }

    private fun setupCategoriesRecyclerView() {
        placeCategoriesAdapter = PlaceCategoriesAdapter()
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.categoriesRv.layoutManager = layoutManager
        binding.categoriesRv.adapter = placeCategoriesAdapter
    }
}