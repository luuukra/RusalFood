package com.example.rusalfood.presentation.sign_in_fragment

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
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
    private val _response: MutableLiveData<String> = MutableLiveData()//data is request status response: ok, or error
    val response: LiveData<String> = _response
    private val _isLoginInputCorrect = MutableLiveData<Boolean>()
    val isLoginInputCorrect: LiveData<Boolean> = _isLoginInputCorrect

    fun checkEmailInput(email: Editable) {
        val pattern: Pattern = Pattern.compile(
            "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z]{2,10})$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(email)
        _isLoginInputCorrect.value = matcher.matches()
    }

    fun signIn(login: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        delay(2000)//todo delete after api implementation
        _response.postValue(signInUseCase(login, password))
    }
}