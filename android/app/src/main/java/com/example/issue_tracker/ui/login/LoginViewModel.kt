package com.example.issue_tracker.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel() {

    fun requestGitHubLogin(clientId: String) {
        viewModelScope.launch {
            loginRepository.requestGitHubLogin(clientId)
        }
    }
}