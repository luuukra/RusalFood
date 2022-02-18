package com.example.rusalfood.presentation.main_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rusalfood.data.network.MockData
import com.example.rusalfood.databinding.FragmentMainBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.Resource

class MainFragment: Fragment(), MainAdapter.onItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainAdapter: MainAdapter

    private val mainViewModel: MainViewModel by viewModels { requireContext().appComponent.mainViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setupRecyclerView()
        setupObserving()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupObserving() {
        mainViewModel.apply {
            listPlaces.observe(viewLifecycleOwner, Observer { status ->
                when (status) {
                    is Resource.Loading -> { binding.shimmerLayout.startShimmer() }
                    is Resource.Success -> { status.data?.let {
                        mainAdapter.diffUtilPlaces.submitList(it)
                        showRecyclerView()
                    }}
                }
            })
        }
    }

    private fun showRecyclerView() {
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.INVISIBLE
        }
        binding.mainRv.visibility = View.VISIBLE
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
}