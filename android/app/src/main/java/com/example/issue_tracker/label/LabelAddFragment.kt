package com.example.issue_tracker.label

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentLabelAddBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LabelAddFragment : Fragment() {

    private lateinit var binding: FragmentLabelAddBinding
    private val viewModel: LabelViewModel by viewModels()

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
    }

    private fun labelColorChange() {
        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.labelColor.collect {
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

}