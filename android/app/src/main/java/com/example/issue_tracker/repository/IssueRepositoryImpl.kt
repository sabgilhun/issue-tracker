package com.example.issue_tracker.repository

import com.example.issue_tracker.datasource.DataSource
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.IssueAddRequest
import com.example.issue_tracker.model.IssueCloseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
) : IssueRepository {

    override suspend fun getIssue(): Flow<List<Issue>> {
        return dataSource.getIssues().map { issueDTO ->
            issueDTO.issues.map { issueDTOItem ->
                Issue.of(issueDTOItem)
            }
        }
    }

    override suspend fun closeIssue(issueId: Int): IssueCloseResponse {
        return dataSource.closeIssue(issueId)
    }

    override suspend fun addIssue(issueAddRequest: IssueAddRequest): Boolean {
        val result = dataSource.addIssue(issueAddRequest)
        return result.isSuccessful
    }

    override suspend fun searchIssue(word: String): Flow<List<Issue>> {
        return dataSource.searchIssue(word).map { issueDTO ->
            issueDTO.issues.map { issueDTOItem ->
                Issue.of(issueDTOItem)
            }
        }
    }
}