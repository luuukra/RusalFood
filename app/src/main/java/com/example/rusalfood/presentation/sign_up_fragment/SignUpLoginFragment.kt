package com.example.rusalfood.presentation.sign_up_fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R
import com.example.rusalfood.databinding.SignUpLoginFragmentBinding
import com.example.rusalfood.di.appComponent

class SignUpLoginFragment : Fragment(R.layout.sign_up_login_fragment) {

    private val binding by viewBinding(SignUpLoginFragmentBinding::bind)
    private val signUpViewModel: SignUpViewModel by viewModels { requireContext().appComponent.signUpViewModelFactory() }

    companion object {
        fun newInstance() = SignUpLoginFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextChangedListeners()
        initClickListeners()
        initObserving()
    }

    private fun initTextChangedListeners() {
        binding.signUpLoginField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                signUpViewModel.checkEmailInput(s)
            }
        })
    }

    private fun initClickListeners() {
        binding.nextButton.setOnClickListener {
            signUpViewModel.navToPasswordFragment(binding.signUpLoginField.text.toString())
        }
    }

    private fun initObserving() {
        signUpViewModel.isEmailInputCorrect.observe(viewLifecycleOwner) {
            binding.nextButton.isEnabled = it
        }

        signUpViewModel.navDirection.observe(viewLifecycleOwner) {
            //if(findNavController().currentDestination?.id != R.id.signUpLoginFragment)
            findNavController().navigate(it)
        }
    }

}