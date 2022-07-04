package com.example.issue_tracker.repository

import com.example.issue_tracker.model.GitHubOAuthRequest
import com.example.issue_tracker.model.LoginRequest
import com.example.issue_tracker.model.LoginResponse
import com.example.issue_tracker.network.APIService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: APIService): LoginRepository {

    override suspend fun requestGitHubLogin(gitHubOAuthRequest: GitHubOAuthRequest): LoginResponse {
        return apiService.requestGitHubLogin(gitHubOAuthRequest)
    }

    override suspend fun requestLogin(loginRequest: LoginRequest): LoginResponse {
        return apiService.requestLogin(loginRequest)
    }
}