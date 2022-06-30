package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Issue

interface IssueRepository {

    suspend fun getIssue(): List<Issue>
}