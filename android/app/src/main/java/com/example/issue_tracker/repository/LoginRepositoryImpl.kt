package com.example.issue_tracker.repository

import com.example.issue_tracker.model.SignUpRequest
import com.example.issue_tracker.model.SignUpResponse
import com.example.issue_tracker.network.APIService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: APIService) : LoginRepository {

    override suspend fun requestSignUp(signUpRequest: SignUpRequest): SignUpResponse {
        return apiService.requestRegister(signUpRequest)
    }
}