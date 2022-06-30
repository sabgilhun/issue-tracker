package com.example.issue_tracker.repository

import com.example.issue_tracker.model.GitHubOAuthRequest
import com.example.issue_tracker.model.OAuthResponse
import com.example.issue_tracker.network.APIService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: APIService): LoginRepository {

    override suspend fun requestGitHubLogin(gitHubOAuthRequest: GitHubOAuthRequest): OAuthResponse {
        return apiService.requestGitHubLogin(gitHubOAuthRequest)
    }
}