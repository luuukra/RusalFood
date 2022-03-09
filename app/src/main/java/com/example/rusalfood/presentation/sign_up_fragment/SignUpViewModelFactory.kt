package com.example.rusalfood.presentation.sign_up_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.SignInUseCase
import com.example.rusalfood.domain.usecases.SignUpUseCase
import javax.inject.Inject

class SignUpViewModelFactory @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(signUpUseCase, signInUseCase) as T
    }
}