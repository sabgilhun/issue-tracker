package com.example.issue_tracker.label

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.CustomLabelBinding

class CustomLabel(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var binding: CustomLabelBinding

    init {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_label, this, true)
        getAttrs(attributeSet)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLabel)

        typedArray.getString(R.styleable.CustomLabel_title)?.let { setLabelText(it) }

        typedArray.recycle()
    }

    private fun setLabelText(text: String) {
        binding.tvCustomLabelTitle.text = text
    }

}