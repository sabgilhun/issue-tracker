package com.example.issue_tracker.ui.issue

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.common.addElement
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.model.MileStone
import com.example.issue_tracker.repository.LabelRepository
import com.example.issue_tracker.repository.MileStoneRepository
import com.example.issue_tracker.ui.milestone.MileStoneAddViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueAddViewModel @Inject constructor(private val labelRepository: LabelRepository, private val mileStoneRepository: MileStoneRepository) : ViewModel() {

    private val _labelList = MutableStateFlow<List<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    private val _mileStoneList = MutableStateFlow<List<MileStone>>(mutableListOf())
    val mileStoneList = _mileStoneList.asStateFlow()

    private val _labelChoose = MutableStateFlow(defaultLabel)
    val labelChoose = _labelChoose.asStateFlow()

    private val _mileStoneChoose = MutableStateFlow(defaultMileStone)
    val mileStoneChoose = _mileStoneChoose.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _labelList.value = labelRepository.getLabelList()
            _mileStoneList.value = mileStoneRepository.getMileStoneList()
        }
    }

    fun findClickedLabelMenu(id: Int) {
        val clickedMenu = labelList.value[id].copy()
        Log.d("issueViewModel", clickedMenu.toString())
        _labelChoose.value = clickedMenu
    }

    fun findClickedMileStoneMenu(id: Int) {
        val clickedMenu = mileStoneList.value[id].copy()
        Log.d("issueViewModel", clickedMenu.toString())
        _mileStoneChoose.value = clickedMenu
    }

    companion object {
        val defaultLabel = Label(
            Label.INITIAL_ID,
            Label.INITIAL_TITLE,
            Label.INITIAL_DESCRIPTION,
            Label.INITIAL_COLOR
        )
        val defaultMileStone =
            MileStone(
                MileStone.INITIAL_COUNTS,
                MileStone.INITIAL_VALUE,
                MileStone.INITIAL_VALUE,
                MileStone.INITIAL_VALUE,
            )
    }
}