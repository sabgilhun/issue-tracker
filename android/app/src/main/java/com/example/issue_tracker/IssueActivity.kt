package com.example.issue_tracker

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.issue_tracker.databinding.ActivityIssueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssueBinding
    private val viewModel: IssueViewModel by viewModels()
    private val issueFragment = IssueFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_issue)
    }

    override fun onStart() {
        super.onStart()
        binding.issueBottomNavigation.setupWithNavController(findNavController(R.id.nav_issue_fragment))

    }
}