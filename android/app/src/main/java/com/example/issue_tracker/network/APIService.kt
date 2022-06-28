package com.example.issue_tracker.network

import com.example.issue_tracker.model.IssueDTO
import com.example.issue_tracker.model.IssueDTOItem
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

@GET("issues")
    suspend fun getIssues(): IssueDTO
}