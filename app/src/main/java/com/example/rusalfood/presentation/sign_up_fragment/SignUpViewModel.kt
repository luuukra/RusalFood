package com.example.rusalfood.presentation.sign_up_fragment

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.usecases.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _emailCheckResponse = MutableLiveData<Boolean>()//data is request status response: ok, or error
    val emailCheckResponse: MutableLiveData<Boolean> = _emailCheckResponse
    private val _signUpResponse = MutableLiveData<String>()
    val signUpResponse: MutableLiveData<String> = _signUpResponse
    private val _isEmailInputCorrect = MutableLiveData<Boolean>()
    val isEmailInputCorrect: MutableLiveData<Boolean> = _isEmailInputCorrect
    private val _isPasswordInputCorrect = MutableLiveData<Boolean>()
    val isPasswordInputCorrect: MutableLiveData<Boolean> = _isPasswordInputCorrect

    val loginAfterTextChangedListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            checkEmailInput(s)
        }
    }

    val passwordAfterTextChangedListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            checkPasswordInput(s)
        }
    }

    private fun checkEmailInput(email: Editable) {
        val pattern: Pattern = Pattern.compile(
            "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z]{2,10})$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(email)
        isEmailInputCorrect.value = matcher.matches()
    }

    private fun checkPasswordInput(password: Editable) {
        val pattern: Pattern = Pattern.compile(
            "^.{9,}$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(password)
        isPasswordInputCorrect.value = matcher.matches()
    }

    fun checkIfEmailAvailable(email: String) = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)//todo delete after api implementation
        _emailCheckResponse.postValue(signUpUseCase.checkEmail(email))
    }

    fun signUpAndGetToken(login: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)//todo delete after api implementation
        _signUpResponse.postValue(signUpUseCase.signUpAndGetToken(login, password))
    }
}