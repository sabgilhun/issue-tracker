package com.example.issue_tracker.network

import com.example.issue_tracker.model.IssueCloseResponse
import com.example.issue_tracker.model.IssueDTO
import com.example.issue_tracker.model.LabelDTO
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("issues")
    suspend fun getIssues(): IssueDTO

    @GET("labels")
    suspend fun getLabels(): LabelDTO

    @POST("labels")
    suspend fun addLabels(@Body label: LabelDTO.LabelDTOItem): Response<Unit>

    @PATCH("issues/{issueId}/status")
    suspend fun closeIssue(@Path("issueId") issueId: Int): IssueCloseResponse
}