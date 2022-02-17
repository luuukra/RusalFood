package com.example.rusalfood.di

import com.example.rusalfood.presentation.sign_in_fragment.SignInViewModelFactory
import com.example.rusalfood.presentation.sign_up_fragment.SignUpViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModules::class)])
interface AppComponent {

    fun signInViewModelFactory(): SignInViewModelFactory

    fun signUpViewModelFactory(): SignUpViewModelFactory

}