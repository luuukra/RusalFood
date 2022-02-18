package com.example.rusalfood.presentation.sign_up_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rusalfood.databinding.SignUpLoginFragmentBinding
import com.example.rusalfood.di.appComponent

class SignUpLoginFragment : Fragment() {

    private var _binding: SignUpLoginFragmentBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by viewModels { requireContext().appComponent.signUpViewModelFactory() }

    companion object {
        fun newInstance() = SignUpLoginFragment()
        const val EMAIL_CHECK_ERROR = "Signing up error: email is already signed up"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextChangedListeners()
        setupClickListeners()
        setupObserving()
    }

    private fun setupTextChangedListeners() {
        binding.signUpLoginField.addTextChangedListener(signUpViewModel.loginAfterTextChangedListener)
    }

    private fun setupClickListeners() {
        binding.nextButton.setOnClickListener {
            signUpViewModel.checkIfEmailAvailable(binding.signUpLoginField.text.toString())
        }
    }

    private fun setupObserving() {
        signUpViewModel.isEmailInputCorrect.observe(viewLifecycleOwner) {
            binding.nextButton.isEnabled = it
        }

        signUpViewModel.emailCheckResponse.observe(viewLifecycleOwner) {
            if (it) {
                findNavController()
                    .navigate(SignUpLoginFragmentDirections.toSignUpPasswordScreen(binding.signUpLoginField.text.toString()))
            } else Toast.makeText(
                context, EMAIL_CHECK_ERROR, Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}