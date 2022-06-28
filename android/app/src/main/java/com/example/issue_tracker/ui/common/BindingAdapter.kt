package com.example.issue_tracker.ui.common

import android.view.View
import android.widget.CheckBox
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun isVisible(view: View, isClicked: Boolean?) {
    view.isVisible = isClicked == true
}
