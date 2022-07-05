package com.example.issue_tracker.datasource

import com.example.issue_tracker.model.*
import retrofit2.Response

interface DataSource {

    suspend fun getIssues(): IssueDTO

    suspend fun getLabels(): LabelDTO

    suspend fun getMileStones(): MileStoneDTO

    suspend fun addMileStones(mileStone: MileStoneDTO.MileStoneDTOItem): Response<Unit>

    suspend fun addLabels(label: LabelDTO.LabelDTOItem): Response<Unit>

    suspend fun closeIssue(issueId: Int): IssueCloseResponse

    suspend fun addIssue(issueAddRequest: IssueAddRequest)
}