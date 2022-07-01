package com.example.issue_tracker.repository

import com.example.issue_tracker.model.GitHubOAuthRequest
import com.example.issue_tracker.model.OAuthResponse

interface LoginRepository {

    suspend fun requestGitHubLogin(gitHubOAuthRequest: GitHubOAuthRequest): OAuthResponse
}