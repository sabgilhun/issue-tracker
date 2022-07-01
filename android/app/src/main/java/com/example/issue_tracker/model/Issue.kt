package com.example.issue_tracker.model

data class Issue(
    val issueId: Int,
    val mileStone: String,
    val title: String,
    val contents: String,
    val label: Label,
    var isSwiped: Boolean = false,
    var isLongClicked: Boolean = false,
    var isChecked: Boolean = false,
) {
    companion object {
        fun of(item: IssueDTO.IssueDTOItem) = Issue(
            issueId = item.issueId,
            mileStone = item.milestoneTitle,
            title = item.title,
            contents = item.description,
            label = Label.of(item.label)
        )
    }
}

data class Label(
    val labelId: Int?,
    val labelTitle: String?,
    val labelContents: String?,
    val labelColor: String?,
) {
    companion object {
        fun of(item: LabelDTO.LabelDTOItem): Label {
            val parsedColorString = item.backgroundColor.substring(2)
            val color = buildString {
                append("#")
                append("FF")
                append(parsedColorString)
            }
            return Label(
                labelId = item.id,
                labelTitle = item.name,
                labelColor = color,
                labelContents = item.description
            )
        }
    }
}

data class MileStone(
    val mileStoneId: Int?,
    val title: String?,
    val description: String?,
    val dueDate: String?,
    val openedIssueCount: Int = 0,
    val closedIssueCount: Int = 0,
    val progress: String = "",
)