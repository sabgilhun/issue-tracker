package com.example.issue_tracker.repository

import com.example.issue_tracker.common.ResponseResult
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.IssueDTO

interface IssueRepository {

    suspend fun getDummyIssue(): MutableList<Issue>

    suspend fun getIssue(): ResponseResult<MutableList<IssueDTO>>
}