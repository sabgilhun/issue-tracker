package com.example.issue_tracker.label

import com.example.issue_tracker.Label
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LabelRepository {

    fun setLabelList(labelList: Label)

    fun getLabelList(): Flow<MutableList<Label>>

}