package kr.codesquad.issuetraker.dto;

import kr.codesquad.issuetraker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MilestoneInfoDto {
    private Long milestoneId;
    private String milestoneName;

    public static MilestoneInfoDto of(Milestone milestone) {
        return new MilestoneInfoDto(milestone.getId(), milestone.getTitle());
    }
}
