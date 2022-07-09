package com.example.issue_tracker.network

import com.example.issue_tracker.common.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppInterceptor @Inject constructor(
    private val preferenceUtil: PreferenceUtil,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val accessToken = preferenceUtil.getString("accessToken", "")
        val newRequest = request().newBuilder()
            .addHeader("authorization", accessToken)
            .build()
        proceed(newRequest)
    }
}