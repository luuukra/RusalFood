package com.example.rusalfood.presentation.sign_in_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.domain.usecases.SignInUseCase
import javax.inject.Inject


class SignInViewModelFactory @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(signInUseCase) as T
    }
}