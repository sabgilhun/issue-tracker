package com.example.issue_tracker.label

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentLabelAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LabelAddFragment : Fragment() {

    private lateinit var binding: FragmentLabelAddBinding
    private val viewModel: LabelAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_label_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        labelColorChange()
        labelTextChange()
        setClickListener()
    }

    private fun labelColorChange() {
        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.label.collect {
                binding.label = it
            }
        }
    }

    private fun labelTextChange() {
        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.labelTitle.collect {
                binding.customLabel.setLabelTitle(it)
            }
        }
    }

    private fun setClickListener() {
        binding.btnLabelSave.setOnClickListener {
            viewModel.saveLabel()
            viewLifecycleOwner.repeatOnLifecycleExtension {
                viewModel.labelList.collect { labelList ->
                    val bundle = bundleOf("label" to labelList)
                    Log.d("LabelAddFragment", labelList.size.toString())
                    findNavController().navigate(R.id.action_labelAddFragment_to_labelFragment, bundle)
                }
            }
        }
    }

}