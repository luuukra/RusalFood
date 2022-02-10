package com.example.rusalfood.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rusalfood.R
import com.example.rusalfood.databinding.SignInFragmentBinding
import com.example.rusalfood.presentation.viewmodels.SignInViewModel

class SignInFragment : Fragment() {

    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        // TODO: Use the ViewModel
        binding.continueGuestButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.to_signUpLogin_screen)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}