package com.example.rusalfood.presentation.sign_up_fragment

import android.content.SharedPreferences
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.rusalfood.domain.models.SignInResponse
import com.example.rusalfood.domain.models.SignUpResponse
import com.example.rusalfood.domain.usecases.SignInUseCase
import com.example.rusalfood.domain.usecases.SignUpUseCase
import com.example.rusalfood.presentation.sign_in_fragment.SignInFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _signUpResponse: MutableLiveData<SignUpResponse> = MutableLiveData()
    val signUpResponse: LiveData<SignUpResponse> = _signUpResponse

    private val _signInResponse: MutableLiveData<SignInResponse> = MutableLiveData()
    val signInResponse: LiveData<SignInResponse> = _signInResponse

    private val _isEmailInputCorrect = MutableLiveData<Boolean>()
    val isEmailInputCorrect: LiveData<Boolean> = _isEmailInputCorrect

    private val _isPasswordInputCorrect = MutableLiveData<Boolean>()
    val isPasswordInputCorrect: LiveData<Boolean> = _isPasswordInputCorrect

    companion object {
        const val SIGN_UP_OK_CODE = 200
        //const val SIGN_UP_ERROR_CODE = 422
    }

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

    fun signUp(login: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)//todo delete after api implementation
            val apiResponse = signUpUseCase(login, password)
            _signUpResponse.postValue(apiResponse)
            if (apiResponse.code == SIGN_UP_OK_CODE) signIn(login, password)
        }

    private fun signIn(login: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = signInUseCase(login, password)
            _signInResponse.postValue(apiResponse)
        }
}
