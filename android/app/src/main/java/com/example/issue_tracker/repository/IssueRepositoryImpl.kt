package com.example.issue_tracker.repository

import com.example.issue_tracker.datasource.DataSource
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.IssueAddRequest
import com.example.issue_tracker.model.IssueCloseResponse
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
) : IssueRepository {

    override suspend fun getIssue(): List<Issue> =
        dataSource.getIssues().issues.map { issueDTOItem ->
            Issue.of(issueDTOItem)
        }

    override suspend fun closeIssue(issueId: Int): IssueCloseResponse {
        return dataSource.closeIssue(issueId)
    }

    override suspend fun addIssue(issueAddRequest: IssueAddRequest) {
        return dataSource.addIssue(issueAddRequest)
    }
}