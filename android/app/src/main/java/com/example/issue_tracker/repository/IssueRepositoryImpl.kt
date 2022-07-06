package com.example.issue_tracker.repository

import com.example.issue_tracker.datasource.DataSource
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.IssueAddRequest
import com.example.issue_tracker.model.IssueCloseResponse
import com.example.issue_tracker.model.IssueFilterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
) : IssueRepository {

    override suspend fun getIssue(issueFilterRequest: IssueFilterRequest): Flow<List<Issue>> {
        return dataSource.getIssues(issueFilterRequest).map { issueDTO ->
            issueDTO.issues.map { issueDTOItem ->
                Issue.of(issueDTOItem)
            }
        }
    }

    override suspend fun closeIssue(issueId: Int): IssueCloseResponse {
        return dataSource.closeIssue(issueId)
    }

    override suspend fun addIssue(issueAddRequest: IssueAddRequest) {
        return dataSource.addIssue(issueAddRequest)
    }

    override suspend fun searchIssue(word: String): Flow<List<Issue>> {
        return dataSource.searchIssue(word).map { issueDTO ->
            issueDTO.issues.map { issueDTOItem ->
                Issue.of(issueDTOItem)
            }
        }
    }
}