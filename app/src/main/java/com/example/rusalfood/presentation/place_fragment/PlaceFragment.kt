package com.example.rusalfood.presentation.place_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.example.rusalfood.R
import com.example.rusalfood.databinding.FragmentPlaceBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.Food


class PlaceFragment : Fragment() {

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    private val args: PlaceFragmentArgs by navArgs()
    private lateinit var placeViewPagerAdapter: PlaceSliderAdapter
    private lateinit var placeFoodListAdapter: PlaceFoodListAdapter

    private val placeViewModel: PlaceViewModel by activityViewModels { requireContext().appComponent.placeViewModelFactory() }

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
        launchObserving()
        initTabLayout()
        setupViewPager()
        setupFoodListRecyclerView()
        setupBasketButton()
        setOnDestinationChangeListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun launchObserving() {
        placeViewModel.currentPlace.observe(viewLifecycleOwner) {
            placeViewPagerAdapter.setData(it.gallery) // Картинки в слайдере
            binding.placeTextViewAddress.text = it.address // Адрес заведения
            placeViewPagerAdapter.notifyDataSetChanged()
        }

        placeViewModel.listOfFoodWithCategories.observe(viewLifecycleOwner) {
            placeFoodListAdapter.setData(it)// Меню ресторана с категориями
            placeFoodListAdapter.notifyDataSetChanged()
        }
    }

    private fun setupCurrentPlace() {
        val place = args.place
        placeViewModel.getIntoPlace(place)
        if (placeViewModel.listOfFoodWithCategories.value.isNullOrEmpty()) {
            placeViewModel.getFoodListById(place.id)
        }
    }

    private fun setupViewPager() {
        placeViewPagerAdapter = PlaceSliderAdapter()
        binding.placeViewPager.adapter = placeViewPagerAdapter
    }

    private fun setupFoodListRecyclerView() {
        placeFoodListAdapter = PlaceFoodListAdapter(placeViewModel, requireContext())
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.foodItemsRv.layoutManager = layoutManager
        binding.foodItemsRv.adapter = placeFoodListAdapter
    }

    private fun initTabLayout() {
        placeViewModel.listOfCategories.observe(viewLifecycleOwner) {
            binding.tabLayout.removeAllTabs()
            it.forEach { category ->
                binding.tabLayout.addTab(
                    binding.tabLayout.newTab().setText(category.categoryName)
                )
            }
            initMediator(placeViewModel.getCategoriesIndexesList())
        }
    }

    private fun initMediator(newIndices: List<Int>) {
        val tabbedListMediator = TabbedListMediator(
            binding.foodItemsRv,
            binding.tabLayout,
            newIndices,
            true
        )
        tabbedListMediator.attach()
        tabbedListMediator.updateMediatorWithNewIndices(newIndices)
    }

    private fun setupBasketButton() {
        placeViewModel.countedFoodList.observe(viewLifecycleOwner) {
            val transition: Transition = Fade()
            transition.duration = 300
            transition.addTarget(binding.basketButtonTemplate.basketFullButton)
            TransitionManager.beginDelayedTransition(binding.root, transition)

            binding.basketButtonTemplate.basketFullButton.run {
                visibility = if (it.isNullOrEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }

        placeViewModel.run {
            totalAmount.observe(viewLifecycleOwner) {
                binding.basketButtonTemplate.amountInc.text = it.toString()
            }
            totalSum.observe(viewLifecycleOwner) {
                binding.basketButtonTemplate.sumInc.text = it.toString()
            }
        }

        binding.basketButtonTemplate.run {
            basketButtonInc.setOnClickListener {
                val placeName = placeViewModel.currentPlace.value?.name
                val placeAddress = placeViewModel.currentPlace.value?.address
                findNavController().navigate(
                    PlaceFragmentDirections.actionPlaceFragmentToBasketFragment(
                        placeName.toString(),
                        placeAddress.toString()
                    )
                )
            }
        }
    }

    private fun setOnDestinationChangeListener() {
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mainFragment) {
                placeViewModel.resetAmounts()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


