package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Label
import com.example.issue_tracker.model.LabelDTO
import kotlinx.coroutines.flow.Flow

interface LabelRepository {

    fun addLabelList(labelList: Label)

    fun getLabelList(): Flow<List<Label>>
}