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
    private lateinit var sharedPref: SharedPreferences

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextChangedListeners()
        initClickListeners()
        initEncryptedSharedPref()
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

    private fun initEncryptedSharedPref() {
        val masterKey = MasterKey.Builder(requireContext(), MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        sharedPref = EncryptedSharedPreferences.create(
            requireContext(),
            "encrypted_shared_pref",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun initClickListeners() {
        binding.signInButton.setOnClickListener {
            binding.loginProgressBar.visibility = ProgressBar.VISIBLE
            signInViewModel.signIn(
                sharedPref,
                binding.signInLoginField.text.toString(),
                binding.signInPasswordField.text.toString()
            )
        }

        binding.continueGuestButton.setOnClickListener {
            signInViewModel.navToMainFragment()
        }

        binding.signUpButton.setOnClickListener {
            signInViewModel.navToSignUpLoginScreen()
        }
    }

    private fun initObserving() {
        signInViewModel.isLoginInputCorrect.observe(viewLifecycleOwner) {
            binding.signInButton.isEnabled = it
        }

        signInViewModel.response.observe(viewLifecycleOwner) {
            Toast.makeText(activity, signInViewModel.response.value?.message, Toast.LENGTH_SHORT)
                .show()
            binding.loginProgressBar.visibility = ProgressBar.GONE
            //println(sharedPref.getString("token", null))
        }

        signInViewModel.navDirection.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }
    }
}