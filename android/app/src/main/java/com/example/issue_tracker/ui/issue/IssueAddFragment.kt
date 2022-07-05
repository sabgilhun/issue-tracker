package com.example.issue_tracker.ui.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentIssueAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueAddFragment : Fragment() {

    private lateinit var binding: FragmentIssueAddBinding
    private val viewModel: IssueAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_issue_add, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val findNavController = findNavController()
        observeMenuButtons()
        findClickedMenu()
        observeClickedMenuText()
        goBackIssue(findNavController)
    }

    private fun observeMenuButtons() {
        with(binding) {
            ibFilterButtonLabel.setOnClickListener {
                viewModel.labelPopupMenu.value?.show()
            }
            ibFilterButtonIssueMileStone.setOnClickListener {
                viewModel.mileStonePopupMenu.value?.show()
            }
        }
    }

    private fun findClickedMenu() {
        with(viewLifecycleOwner) {
            repeatOnLifecycleExtension {
                viewModel.labelPopupMenu.collect { popUpMenu ->
                    popUpMenu?.setOnMenuItemClickListener { item ->
                        viewModel.findClickedLabelMenu(item.itemId)
                        false
                    }
                }
            }
        }
        repeatOnLifecycleExtension {
            viewModel.mileStonePopupMenu.collect { popUpMenu ->
                popUpMenu?.setOnMenuItemClickListener { item ->
                    viewModel.findClickedMileStoneMenu(item.itemId)
                    false
                }
            }
        }
    }

    private fun observeClickedMenuText() {
        with(viewLifecycleOwner) {
            repeatOnLifecycleExtension {
                viewModel.labelChoose.collect {
                    binding.label = it
                }
            }
            repeatOnLifecycleExtension {
                viewModel.mileStoneChoose.collect {
                    binding.mileStone = it
                }
            }
            repeatOnLifecycleExtension {
                viewModel.labelList.collect {
                    context?.let { context ->
                        viewModel.makeLabelPopUpMenu(
                            context,
                            binding.ibFilterButtonLabel
                        )
                    }
                }
            }
            repeatOnLifecycleExtension {
                viewModel.mileStoneList.collect {
                    context?.let { context ->
                        viewModel.makeMileStonePopUpMenu(
                            context,
                            binding.ibFilterButtonIssueMileStone
                        )
                    }
                }
            }
        }
    }

    private fun goBackIssue(findNavController: NavController) {
        binding.ivGoBack.setOnClickListener {
            findNavController.navigate(R.id.action_issueAddFragment_to_issueFragment)
        }
    }
}