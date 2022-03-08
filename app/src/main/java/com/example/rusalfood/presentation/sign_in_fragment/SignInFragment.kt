package com.example.rusalfood.presentation.sign_in_fragment


import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.rusalfood.databinding.SignInFragmentBinding
import com.example.rusalfood.di.appComponent

class SignInFragment : Fragment() {

    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel: SignInViewModel by viewModels { requireContext().appComponent.signInViewModelFactory() }
    private lateinit var sharedPref: SharedPreferences

    companion object {
        fun newInstance() = SignInFragment()
        const val SIGN_IN_OK_CODE = 200
        const val SIGN_IN_ERROR_CODE = 401
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
            findNavController().navigate(SignInFragmentDirections.toMainFragment(false))
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toSignUpLoginScreen())
        }
    }

    @SuppressLint("NewApi")
    private fun initObserving() {
        signInViewModel.isLoginInputCorrect.observe(viewLifecycleOwner) {
            binding.signInButton.isEnabled = it
        }

        signInViewModel.response.observe(viewLifecycleOwner) {
            Toast.makeText(activity, signInViewModel.response.value?.message, Toast.LENGTH_SHORT).show()
            binding.loginProgressBar.visibility = ProgressBar.GONE
            //println(sharedPref.getString("token", null))
            if (it.code == SIGN_IN_OK_CODE)
                findNavController().navigate(SignInFragmentDirections.toMainFragment(true))//todo nav dest in VM
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}