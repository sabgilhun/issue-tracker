package com.example.issue_tracker.datasource

import com.example.issue_tracker.model.IssueDTO
import com.example.issue_tracker.model.LabelDTO
import com.example.issue_tracker.network.APIService
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val apiService: APIService,
) : DataSource {
    override suspend fun getIssues(): IssueDTO = apiService.getIssues()

    override suspend fun addLabels(label: LabelDTO.LabelDTOItem) = apiService.addLabels(label)

    override suspend fun getLabels(): LabelDTO = apiService.getLabels()
}