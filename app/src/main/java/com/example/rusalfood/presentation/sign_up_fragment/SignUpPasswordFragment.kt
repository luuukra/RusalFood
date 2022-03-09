package com.example.rusalfood.presentation.sign_up_fragment


import android.content.Context
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
import com.example.rusalfood.databinding.SignUpPasswordFragmentBinding
import com.example.rusalfood.di.appComponent

class SignUpPasswordFragment : Fragment() {

    private var _binding: SignUpPasswordFragmentBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by viewModels { requireContext().appComponent.signUpViewModelFactory() }
    private lateinit var sharedPref: SharedPreferences

    companion object {
        fun newInstance() = SignUpPasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextChangedListeners()
        initClickListeners()
        initObserving()
        initSharedPref()
    }

    private fun initTextChangedListeners() {
        binding.signUpPasswordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                signUpViewModel.checkPasswordInput(s)
            }
        })
    }

    private fun initClickListeners() {
        binding.nextButton.setOnClickListener {
            binding.loginProgressBar.visibility = ProgressBar.VISIBLE
            signUpViewModel.signUp(
                sharedPref,
                requireArguments().getString("email").toString(),
                binding.signUpPasswordField.text.toString()
            )
        }
    }


    private fun initObserving() {
        signUpViewModel.isPasswordInputCorrect.observe(viewLifecycleOwner) {
            binding.nextButton.isEnabled = it
        }

        signUpViewModel.signUpResponse.observe(viewLifecycleOwner) {
            Toast.makeText(
                activity, signUpViewModel.signUpResponse.value?.message, Toast.LENGTH_SHORT
            ).show()
        }

        signUpViewModel.signInResponse.observe(viewLifecycleOwner) {
            Toast.makeText(activity, signUpViewModel.signInResponse.value?.message, Toast.LENGTH_SHORT)
                .show()
            binding.loginProgressBar.visibility = ProgressBar.GONE
            //println(sharedPref.getString("token", null))
        }

        signUpViewModel.navDirection.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }
    }

    private fun initSharedPref() {
        sharedPref = requireContext().getSharedPreferences("encrypted_shared_pref", Context.MODE_PRIVATE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}