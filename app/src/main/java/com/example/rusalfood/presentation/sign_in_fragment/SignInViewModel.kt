package com.example.rusalfood.presentation.sign_in_fragment

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rusalfood.domain.usecases.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {
    private val _response = MutableLiveData<String>()//data is request status response: ok, or error
    val response: MutableLiveData<String> = _response
    private val _isLoginInputCorrect = MutableLiveData<Boolean>()
    val isLoginInputCorrect: MutableLiveData<Boolean> = _isLoginInputCorrect

    val loginAfterTextChangedListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            isLoginInputCorrect.value = checkEmailInput(s)
        }
    }

    private fun checkEmailInput(email: Editable): Boolean {
        val pattern: Pattern = Pattern.compile(
            "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z]{2,10})$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun signIn(login: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)//todo delete after api implementation
        response.postValue(signInUseCase.signIn(login, password))
    }

}