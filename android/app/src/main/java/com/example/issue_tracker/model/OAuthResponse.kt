package com.example.issue_tracker.model


import com.google.gson.annotations.SerializedName

data class OAuthResponse(
    @SerializedName("accessToken")
    val accessToken: AccessToken,
    @SerializedName("refreshToken")
    val refreshToken: RefreshToken
)

data class AccessToken(
    @SerializedName("expiresAt")
    val expiresAt: String,
    @SerializedName("token")
    val token: String
)

data class RefreshToken(
    @SerializedName("expiresAt")
    val expiresAt: String,
    @SerializedName("token")
    val token: String
)