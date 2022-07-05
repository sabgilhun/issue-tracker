package com.example.issue_tracker.ui.issue

import android.content.Context
import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.model.MileStone
import com.example.issue_tracker.repository.LabelRepository
import com.example.issue_tracker.repository.MileStoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueAddViewModel @Inject constructor(
    private val labelRepository: LabelRepository,
    private val mileStoneRepository: MileStoneRepository,
) : ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    private val _mileStoneList = MutableStateFlow<MutableList<MileStone>>(mutableListOf())
    val mileStoneList = _mileStoneList.asStateFlow()

    private val _labelChoose = MutableStateFlow(defaultLabel)
    val labelChoose = _labelChoose.asStateFlow()

    private val _mileStoneChoose = MutableStateFlow(defaultMileStone)
    val mileStoneChoose = _mileStoneChoose.asStateFlow()

    private val _labelPopUpMenu = MutableStateFlow<PopupMenu?>(null)
    val labelPopupMenu = _labelPopUpMenu.asStateFlow()

    private val _mileStonePopUpMenu = MutableStateFlow<PopupMenu?>(null)
    val mileStonePopupMenu = _mileStonePopUpMenu.asStateFlow()

    init {
        addDummyData()
    }

    private fun addDummyData() {
        viewModelScope.launch {
            _labelList.value = labelRepository.getLabelList().toMutableList()
            _mileStoneList.value = mileStoneRepository.getMileStoneList().toMutableList()
        }.onJoin
    }

    fun findClickedLabelMenu(id: Int) {
        val clickedMenu = labelList.value[id].copy()
        _labelChoose.value = clickedMenu
    }

    fun findClickedMileStoneMenu(id: Int) {
        val clickedMenu = mileStoneList.value[id].copy()
        _mileStoneChoose.value = clickedMenu
    }

    fun makeLabelPopUpMenu(context: Context, view: View) {
        _labelPopUpMenu.value = PopupMenu(context, view).apply {
            labelList.value.forEachIndexed { index, item ->
                menu.add(Menu.NONE, index, index, item.labelTitle)
            }
        }
    }

    fun makeMileStonePopUpMenu(context: Context, view: View) {
        _mileStonePopUpMenu.value = PopupMenu(context, view).apply {
            mileStoneList.value.forEachIndexed { index, item ->
                menu.add(Menu.NONE, index, index, item.title)
            }
        }
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