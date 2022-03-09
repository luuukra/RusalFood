package com.example.rusalfood.presentation.main_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R
import com.example.rusalfood.databinding.FragmentMainBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.Resource

class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.OnItemClickListener {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private lateinit var mainAdapter: MainAdapter

    private val mainViewModel: MainViewModel by viewModels { requireContext().appComponent.mainViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initAuthorizationFlag()
        initObserving()
    }

    private fun initAuthorizationFlag() {
        mainViewModel.isAuthorized.value = requireArguments().getBoolean("isAuthorized")
    }

    private fun initObserving() {
        mainViewModel.run {
            placesList.observe(viewLifecycleOwner) { status ->
                when (status) {
                    is Resource.Success -> {
                        status.data?.let {
                            mainAdapter.setData(it)
                            showRecyclerView()
                        }
                    }
                }
            }
        }

        mainViewModel.navDirection.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }
    }

    private fun showRecyclerView() {
        binding.shimmerLayout.run {
            stopShimmer()
            visibility = View.INVISIBLE
        }
        binding.mainRecyclerView.visibility = View.VISIBLE
    }

    private fun initRecyclerView() {
        mainAdapter = MainAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.layoutManager = layoutManager
        binding.mainRecyclerView.adapter = mainAdapter
    }

    override fun onItemClick(position: Int, placeName: String, placeId: Int) {
        mainViewModel.navToPlaceFragment(placeName, placeId)
    }
}