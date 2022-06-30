package com.example.issue_tracker.network

import com.example.issue_tracker.model.IssueDTO
import com.example.issue_tracker.model.LabelListDTO
import com.example.issue_tracker.model.SignUpRequest
import com.example.issue_tracker.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @GET("issues")
    suspend fun getIssues(): IssueDTO

    @GET("labels")
    suspend fun getLabels(): LabelListDTO

    @POST("register")
    suspend fun requestRegister(@Body signUpRequest: SignUpRequest): SignUpResponse

    @GET("https://github.com/login/oauth/authorize")
    suspend fun requestGitHubLogin(
        @Query("client_id") clientId: String
    )
}