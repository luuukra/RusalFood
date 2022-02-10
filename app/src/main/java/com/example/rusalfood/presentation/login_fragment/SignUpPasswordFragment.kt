package com.example.rusalfood.presentation.login_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rusalfood.databinding.SignUpPasswordFragmentBinding

class SignUpPasswordFragment : Fragment() {

    private var _binding: SignUpPasswordFragmentBinding? = null
    private val binding get() = _binding!!

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
        binding.nextButton.setOnClickListener {
            //слушает дату, если ок, то дальше
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}