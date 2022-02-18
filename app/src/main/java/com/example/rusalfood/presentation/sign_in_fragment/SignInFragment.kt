package com.example.rusalfood.presentation.sign_in_fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rusalfood.databinding.SignInFragmentBinding
import com.example.rusalfood.di.appComponent

class SignInFragment : Fragment() {

    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel: SignInViewModel by viewModels { requireContext().appComponent.signInViewModelFactory() }

    companion object {
        fun newInstance() = SignInFragment()
        const val AUTH_OK = "Authentication successful"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextChangedListeners()
        setupClickListeners()
        setupObserving()
    }

    private fun setupTextChangedListeners() {
        binding.signInLoginField.addTextChangedListener(signInViewModel.loginAfterTextChangedListener)
    }

    private fun setupClickListeners() {
        binding.signInButton.setOnClickListener {
            binding.loginProgressBar.visibility = ProgressBar.VISIBLE
            signInViewModel.signIn(
                binding.signInLoginField.text.toString(),
                binding.signInPasswordField.text.toString()
            )
        }

        binding.continueGuestButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toMainFragment(false))
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toSignUpLoginScreen())
        }
    }

    @SuppressLint("NewApi")
    private fun setupObserving() {
        signInViewModel.isLoginInputCorrect.observe(viewLifecycleOwner) {
            binding.signInButton.isEnabled = it
        }

        signInViewModel.response.observe(viewLifecycleOwner) {
            if (it.equals(AUTH_OK))
                findNavController().navigate(SignInFragmentDirections.toMainFragment(true))
            else {
                Toast.makeText(context, signInViewModel.response.value, Toast.LENGTH_SHORT).show()
                binding.loginProgressBar.visibility = ProgressBar.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}