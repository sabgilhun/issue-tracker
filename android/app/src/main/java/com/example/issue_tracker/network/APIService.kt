package com.example.issue_tracker.network

import com.example.issue_tracker.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("issues")
    suspend fun getIssues(): IssueDTO

    @GET("labels")
    suspend fun getLabels(): LabelDTO

    @POST("register")
    suspend fun requestRegister(@Body signUpRequest: SignUpRequest): SignUpResponse

    @POST("login/oauth")
    suspend fun requestGitHubLogin(
        @Body gitHubOAuthRequest: GitHubOAuthRequest
    ): OAuthResponse

    @POST("labels")
    suspend fun addLabels(@Body label: LabelDTO.LabelDTOItem): Response<Unit>
}