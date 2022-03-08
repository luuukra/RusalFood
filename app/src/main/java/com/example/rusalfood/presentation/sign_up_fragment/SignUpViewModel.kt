package com.example.rusalfood.presentation.sign_up_fragment

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.models.SignUpResponse
import com.example.rusalfood.domain.usecases.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpResponse: MutableLiveData<SignUpResponse> = MutableLiveData()
    val signUpResponse: LiveData<SignUpResponse> = _signUpResponse
    private val _isEmailInputCorrect = MutableLiveData<Boolean>()
    val isEmailInputCorrect: LiveData<Boolean> = _isEmailInputCorrect
    private val _isPasswordInputCorrect = MutableLiveData<Boolean>()
    val isPasswordInputCorrect: LiveData<Boolean> = _isPasswordInputCorrect

    fun checkEmailInput(email: Editable) {
        val pattern: Pattern = Pattern.compile(
            "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z]{2,10})$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(email)
        _isEmailInputCorrect.value = matcher.matches()
    }

    fun checkPasswordInput(password: Editable) {
        val pattern: Pattern = Pattern.compile(
            "^.{9,}$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(password)
        _isPasswordInputCorrect.value = matcher.matches()
    }

    fun signUpAndGetToken(login: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)//todo delete after api implementation
        _signUpResponse.postValue(signUpUseCase(login, password))
    }
}