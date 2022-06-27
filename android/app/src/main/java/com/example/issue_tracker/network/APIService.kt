package com.example.issue_tracker.network

import com.example.issue_tracker.model.IssueDTO
import retrofit2.http.GET

interface APIService {

@GET("issues")
fun getIssues(): ArrayList<IssueDTO>
}