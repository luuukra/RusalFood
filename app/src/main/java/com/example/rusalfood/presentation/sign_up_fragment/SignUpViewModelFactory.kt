package com.example.rusalfood.presentation.sign_up_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rusalfood.data.network.MockLoginData
import com.example.rusalfood.data.repositories.LoginRepositoryImpl
import com.example.rusalfood.domain.usecases.SignUpUseCase
import javax.inject.Inject

class SignUpViewModelFactory @Inject constructor (private val mockLoginData: MockLoginData) : ViewModelProvider.Factory {
    private val loginRepository = LoginRepositoryImpl(mockLoginData)
    private val signUpUseCase = SignUpUseCase(loginRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(signUpUseCase) as T
    }
}