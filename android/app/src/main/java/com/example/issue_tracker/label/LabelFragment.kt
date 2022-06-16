package com.example.issue_tracker.label

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.Label
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentLabelBinding
import kotlinx.coroutines.flow.collect

class LabelFragment : Fragment() {

    private lateinit var binding: FragmentLabelBinding
    private val viewModel: LabelViewModel by viewModels()
    private val adapter = LabelListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_label, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val incomingData = arguments?.getSerializable("label") as MutableList<Label>?
        Log.d("LabelFragment", incomingData?.size.toString())

        viewModel.setViewModelLabelList(incomingData)

        binding.rvLabelList.adapter = adapter

        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.labelList.collect { labelList ->
                adapter.submitList(labelList)
            }
        }

        val findNavControl = findNavController()
        with(binding) {
            ibAddNewLabel.setOnClickListener {
                findNavControl.navigate(R.id.action_labelFragment_to_labelAddFragment)
            }
        }
    }
}