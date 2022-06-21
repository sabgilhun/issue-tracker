package com.example.issue_tracker.ui.issue

import androidx.lifecycle.ViewModel
import com.example.issue_tracker.model.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.issue_tracker.common.addElement
import com.example.issue_tracker.model.MileStone


@HiltViewModel
class IssueFilterViewModel @Inject constructor() : ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    private val _mileStoneList = MutableStateFlow<MutableList<MileStone>>(mutableListOf())
    val mileStoneList = _mileStoneList.asStateFlow()

    private val _statusChoose = MutableStateFlow("열린 이슈")
    val statusChoose = _statusChoose.asStateFlow()

    fun addDummyData() {
        _labelList.addElement(Label(1, "Label1", "Contents1", "#FFFFFF"))
        _labelList.addElement(Label(2, "Label2", "Contents2", "#FFFFFF"))
        _mileStoneList.addElement(MileStone(1, "MileStone1", "Contents1", "2022-06-13"))
        _mileStoneList.addElement(MileStone(1, "MileStone2", "Contents2", "2022-06-13"))
    }

    fun setStatusTitle(title: String) {
        _statusChoose.value = title
    }
}