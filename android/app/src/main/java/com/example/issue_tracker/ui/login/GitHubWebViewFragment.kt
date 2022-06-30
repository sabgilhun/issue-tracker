package com.example.issue_tracker.ui.login

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.FragmentGitHubWebViewBinding
import com.example.issue_tracker.model.GitHubOAuthRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubWebViewFragment : Fragment() {

    lateinit var binding: FragmentGitHubWebViewBinding
    private val viewModel: LoginViewModel by viewModels()

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
            webViewClient = CustomWebViewClient()
            loadUrl("https://github.com/login/oauth/authorize?client_id=" + getString(R.string.git_hub_id))
        }
    }

    inner class CustomWebViewClient : WebViewClient() {

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            Log.d("Git", request?.url.toString())
            if (request?.url.toString().startsWith("http://52.79.243.28:8080/")) {
                val authCode = request?.url.toString().split("=")[1]
                Log.d("Git, AuthCode", authCode)
                viewModel.requestGitHubLogin(GitHubOAuthRequest(authCode))
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}