package com.example.issue_tracker.repository

import android.util.Log
import com.example.issue_tracker.network.APIService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: APIService): LoginRepository {

    override suspend fun requestGitHubLogin(clientId: String) {
        val response = apiService.requestGitHubLogin(clientId)
    }
}