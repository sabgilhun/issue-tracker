package com.example.issue_tracker.repository

interface LoginRepository {

    suspend fun requestGitHubLogin(clientId: String)
}