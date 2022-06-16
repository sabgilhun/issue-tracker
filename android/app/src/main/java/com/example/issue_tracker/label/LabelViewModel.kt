package com.example.issue_tracker.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelViewModel @Inject constructor(
    private val labelRepository: LabelRepository
): ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    init {
        viewModelScope.launch {
            labelRepository.getLabelList().collect {
                _labelList.value = it
            }
        }
    }

    fun setViewModelLabelList(label: MutableList<Label>?) {
        if(label != null) {
            _labelList.value = label
        }
    }

}