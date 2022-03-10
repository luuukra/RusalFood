package com.example.rusalfood.presentation.sign_in_fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rusalfood.R
import com.example.rusalfood.databinding.SignInFragmentBinding
import com.example.rusalfood.di.appComponent

class SignInFragment : Fragment(R.layout.sign_in_fragment) {

    private val binding by viewBinding(SignInFragmentBinding::bind)
    private val signInViewModel: SignInViewModel by viewModels { requireContext().appComponent.signInViewModelFactory() }

    companion object {
        fun newInstance() = SignInFragment()
            const val SIGN_IN_OK_CODE = 200
            //const val SIGN_IN_ERROR_CODE = 401
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextChangedListeners()
        initClickListeners()
        initObserving()
    }

    private fun initTextChangedListeners() {
        binding.signInLoginField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                signInViewModel.checkEmailInput(s)
            }
        })
    }

    private fun initClickListeners() {
        binding.signInButton.setOnClickListener {
            binding.signInProgressBar.visibility = ProgressBar.VISIBLE
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

    private fun initObserving() {
        signInViewModel.isLoginInputCorrect.observe(viewLifecycleOwner) {
            binding.signInButton.isEnabled = it
        }

        signInViewModel.response.observe(viewLifecycleOwner) {
            Toast.makeText(activity, signInViewModel.response.value?.message, Toast.LENGTH_SHORT)
                .show()
            binding.signInProgressBar.visibility = ProgressBar.GONE
            if (it.code == SIGN_IN_OK_CODE)
                findNavController().navigate(SignInFragmentDirections.toMainFragment(true))
        //println(sharedPref.getString("token", null))
        }
    }
}