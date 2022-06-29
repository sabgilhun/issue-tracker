package com.example.issue_tracker.model

import com.google.gson.annotations.SerializedName

data class IssueDTO(
    @SerializedName("issues")
    val issues: List<IssueDTOItem>,
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
    val title: String,
)

data class LabelDTO(
    @SerializedName("backgroundColor")
    val backgroundColor: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)

fun List<IssueDTOItem>.toClientIssue(): MutableList<Issue> {
    return this.map { issueDTOItem ->
        val issueId = requireNotNull(issueDTOItem.issueId)
        val mileStone = issueDTOItem.milestoneTitle
        val title = requireNotNull(issueDTOItem.title)
        val contents = issueDTOItem.description
        val label = Label(
            labelId = requireNotNull(issueDTOItem.label.id),
            labelTitle = requireNotNull(issueDTOItem.label.name),
            labelColor = issueDTOItem.label.backgroundColor,
            labelContents = issueDTOItem.label.description
        )

        Issue(
            issueId = issueId,
            mileStone = mileStone,
            title = title,
            contents = contents,
            label = label
        )
    }.toMutableList()
}

