package com.example.rusalfood.presentation.login_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rusalfood.R
import com.example.rusalfood.databinding.SignUpLoginFragmentBinding

class SignUpLoginFragment : Fragment() {

    private var _binding: SignUpLoginFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SignUpLoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.to_signUpPassword_screen)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}