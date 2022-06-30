package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.network.APIService
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val apiService: APIService,
) : IssueRepository {

    override suspend fun getIssue(): List<Issue> =
        apiService.getIssues().issues.map { issueDTOItem ->
            Issue.of(issueDTOItem)
        }
}