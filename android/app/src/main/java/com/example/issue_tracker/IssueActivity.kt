package com.example.issue_tracker

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_issue)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.issueBottomNavigation.setupWithNavController(findNavController(R.id.nav_issue_fragment))
    }

    // 화면 아무 곳이나 클릭 하면 키보드 숨김
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }
}