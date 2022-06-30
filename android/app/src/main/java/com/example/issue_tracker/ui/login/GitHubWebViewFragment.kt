package com.example.issue_tracker.ui.login

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.FragmentGitHubWebViewBinding

class GitHubWebViewFragment : Fragment() {

    lateinit var binding: FragmentGitHubWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_web_view, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.githubWebView.run {
            webViewClient = CustomWebViewClient()  // WebViewClient() 의 경우 페이지가 정상적으로 load 되지만 CustomWebViewClient일 경우 로드되지 않음
            loadUrl("https://github.com/login/oauth/authorize?client_id=" + getString(R.string.git_hub_id))
        }
    }

    inner class CustomWebViewClient : WebViewClient() {

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            Log.d("웹뷰", request?.url.toString())
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}