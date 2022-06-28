package com.example.issue_tracker.model

import com.google.gson.annotations.SerializedName

data class IssueDTO (
    @SerializedName("issues")
    val issues: List<IssueDTOItem>
    )

data class IssueDTOItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("issueId")
    val issueId: Int,
    @SerializedName("label")
    val label: LabelDTO,
    @SerializedName("milestoneTitle")
    val milestoneTitle: String,
    @SerializedName("title")
    val title: String
)

data class LabelDTO(
    @SerializedName("backgroundColor")
    val backgroundColor: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)