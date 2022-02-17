package com.example.rusalfood.presentation.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.databinding.FragmentMainBinding

class MainFragment: Fragment(), MainAdapter.onItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mockData: MockData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        setupObserving()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserving() {
        mainViewModel.listPlaces.observe(viewLifecycleOwner, {
            mainAdapter.places = it
        })
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.mainRv.layoutManager = layoutManager
        binding.mainRv.adapter = mainAdapter
    }

    override fun onItemClick(positionn: Int, placeName: String, placeId: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToPlaceFragment(placeName, placeId)
        )
    }

    private fun setupViewModel() {
        mockData = MockData()
        mainViewModelFactory = MainViewModelFactory(mockData)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
    }
}