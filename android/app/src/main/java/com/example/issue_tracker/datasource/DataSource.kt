package com.example.issue_tracker.datasource

import com.example.issue_tracker.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DataSource {

    suspend fun getIssues(issueFilterRequest: IssueFilterRequest): Flow<IssueDTO>

    suspend fun getLabels(): LabelDTO

    suspend fun getMileStones(): MileStoneDTO

    suspend fun addMileStones(mileStone: MileStoneDTO.MileStoneDTOItem): Response<Unit>

    suspend fun addLabels(label: LabelDTO.LabelDTOItem): Response<Unit>

    suspend fun closeIssue(issueId: Int): IssueCloseResponse

    suspend fun addIssue(issueAddRequest: IssueAddRequest)

    suspend fun searchIssue(word: String): Flow<IssueDTO>
}