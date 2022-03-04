package com.example.rusalfood.presentation.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rusalfood.databinding.FragmentMainBinding
import com.example.rusalfood.di.appComponent
import com.example.rusalfood.domain.models.Resource

class MainFragment: Fragment(), MainAdapter.OnItemClickListener {

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
        setAuthorizationFlag()
        setupObserving()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAuthorizationFlag() {
        mainViewModel.isAuthorized.value = requireArguments().getBoolean("isAuthorized")
    }

    private fun setupObserving() {
        mainViewModel.apply {
            placesList.observe(viewLifecycleOwner) { status ->
                when (status) {
                    is Resource.Success -> { status.data?.let {
                        mainAdapter.setData(it)
                        showRecyclerView()
                    }}
                }
            }
        }
    }

    private fun showRecyclerView() {
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.INVISIBLE
        }
        binding.mainRecyclerView.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.layoutManager = layoutManager
        binding.mainRecyclerView.adapter = mainAdapter
    }

    override fun onItemClick(position: Int, placeName: String, placeId: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToPlaceFragment(
                placeName,
                mainViewModel.placesList.value!!.data!![placeId]
            )
        )
    }
}