package com.example.issue_tracker.ui.issue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentIssueAddBinding
import com.example.issue_tracker.ui.milestone.MileStoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        viewModel.loadData()
        observeMenuButtons()
        observeClickedMenuText()
        goBackIssue(findNavController)
    }

    private fun observeMenuButtons() {
        with(binding) {
            ibFilterButtonLabel.setOnClickListener {
                val labelList = viewModel.labelList.value
                Log.d("issueViewModel", labelList.toString())
                val labelPopupMenu = PopupMenu(requireContext(), binding.ibFilterButtonLabel).apply {
                    labelList.forEachIndexed { index, item ->
                        menu.add(Menu.NONE, index, index, item.labelTitle)
                        Log.d("issueViewModel",index.toString())
                    }
                }
                labelPopupMenu.setOnMenuItemClickListener { item ->
                    viewModel.findClickedLabelMenu(item.itemId)
                    false
                }
                labelPopupMenu.show()
            }
            ibFilterButtonIssueMileStone.setOnClickListener {
                val mileStoneList = viewModel.mileStoneList.value
                Log.d("issueViewModel", mileStoneList.toString())
                val mileStonePopupMenu = PopupMenu(requireContext(), binding.ibFilterButtonIssueMileStone).apply {
                    mileStoneList.forEachIndexed { index, item ->
                        menu.add(Menu.NONE, index, index, item.title)
                        Log.d("issueViewModel",index.toString())
                    }
                }
                mileStonePopupMenu.setOnMenuItemClickListener { item ->
                    viewModel.findClickedMileStoneMenu(item.itemId)
                    false
                }
                mileStonePopupMenu.show()
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
        }
    }

    private fun goBackIssue(findNavController: NavController) {
        binding.ivGoBack.setOnClickListener {
            findNavController.navigate(R.id.action_issueAddFragment_to_issueFragment)
        }
    }
}