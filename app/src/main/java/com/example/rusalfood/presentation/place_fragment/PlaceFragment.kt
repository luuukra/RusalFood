package com.example.rusalfood.presentation.place_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent

class PlaceFragment : Fragment() {

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    private val args: PlaceFragmentArgs by navArgs()
    private lateinit var placeViewPagerAdapter: PlaceSliderAdapter
    private lateinit var placeFoodListAdapter: PlaceFoodListAdapter

    private val placeViewModel: PlaceViewModel by viewModels { requireContext().appComponent.placeViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCurrentPlace()
        initTabLayout()
        setupViewPager()
        setupFoodListRecyclerView()
        launchObserving()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun launchObserving() {
        placeViewModel.currentPlace.observe(viewLifecycleOwner) {
            placeViewPagerAdapter.vpImages = it.gallery // Картинки в слайдере
            binding.placeTvAddress.text = it.address // Адрес заведения
            placeViewPagerAdapter.notifyDataSetChanged()
        }

        placeViewModel.listOfFoodWithCategories.observe(viewLifecycleOwner) {
            placeFoodListAdapter.foodList = it// Меню ресторана с категориями
            placeFoodListAdapter.notifyDataSetChanged()
        }
    }

    private fun setupCurrentPlace() {
        val placeId = args.placeId
        placeViewModel.getIntoPlace(placeId)
        placeViewModel.getFoodListById(placeId)
    }


    private fun setupViewPager() {
        placeViewPagerAdapter = PlaceSliderAdapter()
        binding.viewPager.adapter = placeViewPagerAdapter
    }

    private fun setupFoodListRecyclerView() {
        placeFoodListAdapter = PlaceFoodListAdapter()
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.foodItemsRv.layoutManager = layoutManager
        binding.foodItemsRv.adapter = placeFoodListAdapter
    }

    private fun initTabLayout() {
        placeViewModel.listOfCategories.observe(viewLifecycleOwner) {
            it.forEach { category ->
                binding.tabLayout.addTab(
                    binding.tabLayout.newTab().setText(category.categoryName)
                )
            }
            initMediator()
        }
    }

    private fun initMediator() {
        placeViewModel.listOfCategoriesIndexes.observe(viewLifecycleOwner) {
            TabbedListMediator(
                binding.foodItemsRv,
                binding.tabLayout,
                it,
                true
            ).attach()
        }
    }
}


