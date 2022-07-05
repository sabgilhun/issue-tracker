package com.example.issue_tracker.network

import com.example.issue_tracker.model.*
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("issues")
    suspend fun getIssues(): IssueDTO

    @GET("labels")
    suspend fun getLabels(): LabelDTO

    @GET("milestones")
    suspend fun getMileStones(): MileStoneDTO

    @POST("labels")
    suspend fun addLabels(@Body label: LabelDTO.LabelDTOItem): Response<Unit>

    @POST("milestones")
    suspend fun addMilestones(@Body mileStone: MileStoneDTO.MileStoneDTOItem): Response<Unit>

    @PATCH("issues/{issueId}/status")
    suspend fun closeIssue(@Path("issueId") issueId: Int): IssueCloseResponse

    @POST("issues")
    suspend fun addIssie(@Body request: IssueAddRequest)
}