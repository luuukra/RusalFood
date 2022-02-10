package com.example.rusalfood.presentation.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rusalfood.databinding.MainFragmentMockBinding

class MainFragmentMock : Fragment() {
    private var _binding: MainFragmentMockBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragmentMock()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentMockBinding.inflate(inflater, container, false)
        return binding.root
    }
}