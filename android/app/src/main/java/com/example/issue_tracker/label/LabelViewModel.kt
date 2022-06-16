package com.example.issue_tracker.label

import android.graphics.Color
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.BR
import com.example.issue_tracker.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelViewModel @Inject constructor() : ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())

    private val _labelColor = MutableStateFlow(Label(null, INITIAL_TITLE, null, INITIAL_COLOR))
    val labelColor = _labelColor.asStateFlow()

    val labelTitle = MutableStateFlow(INITIAL_TITLE)

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

        _labelColor.value = Label(null, labelTitle.value, null, randomColorStringBuilder.toString())
    }

    private fun convertIntToHex(number: Int): String {
        return String.format("%02x", number)
    }

    companion object {
        const val INITIAL_COLOR = "#FFFFFFFF"
        const val INITIAL_TITLE = "label"
    }

}