package com.example.rusalfood.presentation.sign_in_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.data.network.MockLoginData
import com.example.rusalfood.data.repositories.LoginRepositoryImpl
import com.example.rusalfood.domain.usecases.SignInUseCase
import javax.inject.Inject


class SignInViewModelFactory @Inject constructor (private val mockLoginData: MockLoginData) : ViewModelProvider.Factory {
    private val loginRepository = LoginRepositoryImpl(mockLoginData)
    private val signInUseCase = SignInUseCase(loginRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(signInUseCase) as T
    }
}