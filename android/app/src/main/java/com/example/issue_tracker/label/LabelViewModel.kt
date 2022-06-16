package com.example.issue_tracker.label

import androidx.lifecycle.ViewModel
import com.example.issue_tracker.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LabelViewModel @Inject constructor(): ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    fun setViewModelLabelList(label: MutableList<Label>?) {
        if(label != null) {
            _labelList.value = label
        }
    }

}