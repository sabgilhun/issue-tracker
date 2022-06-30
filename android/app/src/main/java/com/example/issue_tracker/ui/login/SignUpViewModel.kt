package com.example.issue_tracker.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.model.SignUpRequest
import com.example.issue_tracker.model.SignUpResponse
import com.example.issue_tracker.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    private val _signUpResponse = MutableSharedFlow<SignUpResponse>()
    val signUpResponse: SharedFlow<SignUpResponse> = _signUpResponse

    fun requestSignUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            val response = loginRepository.requestSignUp(signUpRequest)
            Log.d("로그인", response.toString())
        }
    }
}