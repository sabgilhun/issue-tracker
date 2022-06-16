package com.example.issue_tracker.label

import androidx.lifecycle.ViewModel
import com.example.issue_tracker.Label
import com.example.issue_tracker.common.setList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LabelAddViewModel @Inject constructor() : ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    private val _label = MutableStateFlow(Label(null, INITIAL_TITLE, null, INITIAL_COLOR))
    val label = _label.asStateFlow()

    val labelTitle = MutableStateFlow(INITIAL_TITLE)

    val labelDescription = MutableStateFlow(INITIAL_DESCRIPTION)

    fun randomColor() {
        val randomA = 255
        val randomR = (0..255).random()
        val randomG = (0..255).random()
        val randomB = (0..255).random()
        val colorA = convertIntToHex(randomA)
        val colorR = convertIntToHex(randomR)
        val colorG = convertIntToHex(randomG)
        val colorB = convertIntToHex(randomB)
        val randomColorStringBuilder = StringBuilder("")
        randomColorStringBuilder.append("#")
        randomColorStringBuilder.append(colorA)
        randomColorStringBuilder.append(colorR)
        randomColorStringBuilder.append(colorG)
        randomColorStringBuilder.append(colorB)

        _label.value = Label(null, labelTitle.value, labelDescription.value, randomColorStringBuilder.toString())
    }

    fun saveLabel() {
        _labelList.setList(_label.value)
    }

    private fun convertIntToHex(number: Int): String {
        return String.format("%02x", number)
    }

    companion object {
        const val INITIAL_COLOR = "#FFFFFFFF"
        const val INITIAL_TITLE = "label"
        const val INITIAL_DESCRIPTION = "label 에 설명을 추가해주세요"

    }

}