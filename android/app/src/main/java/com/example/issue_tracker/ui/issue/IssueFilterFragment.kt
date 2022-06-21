package com.example.issue_tracker.ui.issue

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.FragmentIssueFilterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueFilterFragment : Fragment() {

    lateinit var binding: FragmentIssueFilterBinding
    private val viewModel: IssueFilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_filter, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val findNavController = findNavController()
        goBackIssue(findNavController)
        viewModel.addDummyData()

        binding.ibFilterButtonStatus.setOnClickListener {
            val statusPopupMenu = PopupMenu(requireContext(), it)
            statusPopupMenu.menuInflater.inflate(R.menu.issue_filter_status_menu, statusPopupMenu.menu)
            statusPopupMenu.show()
        }

        binding.ibFilterButtonLabel.setOnClickListener {
            val labelPopupMenu = PopupMenu(requireContext(), it)
            for (i in 0 until viewModel.labelList.value.size) {
                labelPopupMenu.menu.add(Menu.NONE, i, i, viewModel.labelList.value[i].labelTitle)
            }
            labelPopupMenu.show()
        }

        binding.ibFilterButtonMileStone.setOnClickListener {
            val mileStonePopupMenu = PopupMenu(requireContext(), it)
            for (i in 0 until viewModel.labelList.value.size) {
                mileStonePopupMenu.menu.add(Menu.NONE, i, i, viewModel.mileStoneList.value[i].title)
            }
            mileStonePopupMenu.show()
        }
    }

    private fun goBackIssue(findNavController: NavController) {
        binding.btnCloseIssueFilter.setOnClickListener {
            findNavController.navigate(R.id.action_issueFilterFragment_to_issueFragment)
        }
    }
}