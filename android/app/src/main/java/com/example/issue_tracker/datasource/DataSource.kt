package com.example.issue_tracker.datasource

import com.example.issue_tracker.model.IssueCloseResponse
import com.example.issue_tracker.model.IssueDTO
import com.example.issue_tracker.model.LabelDTO
import retrofit2.Response

interface DataSource {

    suspend fun getIssues(): IssueDTO

    suspend fun getLabels(): LabelDTO

    suspend fun addLabels(label: LabelDTO.LabelDTOItem): Response<Unit>

    suspend fun closeIssue(issueId: Int): IssueCloseResponse
}