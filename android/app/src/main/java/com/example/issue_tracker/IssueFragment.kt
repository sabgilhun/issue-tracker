package com.example.issue_tracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.issue_tracker.databinding.FragmentIssueBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class IssueFragment : Fragment() {

    lateinit var binding: FragmentIssueBinding
    lateinit var adapter: IssueAdapter
    private val viewModel: IssueViewModel by activityViewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getIssue()
        adapter = IssueAdapter()
        val swipeHelperCallback = SwipeHelperCallback(adapter).apply {
            setClamp(resources.displayMetrics.widthPixels.toFloat() / 4)
        }
        binding.rvIssue.adapter = adapter
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvIssue)

//        view.setOnTouchListener { _, motionEvent ->
//            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
//                swipeHelperCallback.removePreviousClamp(binding.rvIssue)
//            }
//            false
//        }

        binding.rvIssue.setOnTouchListener { _, _ ->
            swipeHelperCallback.removePreviousClamp(binding.rvIssue)
            false
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.issue.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}