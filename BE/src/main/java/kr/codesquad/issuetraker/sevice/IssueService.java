package kr.codesquad.issuetraker.sevice;

import kr.codesquad.issuetraker.domain.issue.Issue;
import kr.codesquad.issuetraker.domain.issue.IssueRepository;
import kr.codesquad.issuetraker.domain.label.Label;
import kr.codesquad.issuetraker.domain.label.LabelRepository;
import kr.codesquad.issuetraker.domain.milestone.Milestone;
import kr.codesquad.issuetraker.domain.milestone.MilestoneRepository;
import kr.codesquad.issuetraker.domain.user.User;
import kr.codesquad.issuetraker.domain.user.UserRepository;
import kr.codesquad.issuetraker.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final MilestoneRepository milestoneRepository;
    private final LabelRepository labelRepository;

    public List<IssueListResponseDto> getAllIssues() {
        List<Issue> issues = issueRepository.findAllAvailableIssues();
        return issues.stream()
                .map(IssueListResponseDto::of)
                .collect(Collectors.toList());
    }

    public NewIssueResponseDto createIssue(NewIssueRequestDto requestDto) {
        User author = userRepository.findById(requestDto.getAuthorId()).orElseThrow(() -> new RuntimeException());
        User assignee = userRepository.findById(requestDto.getAssigneeId()).orElseThrow(() -> new RuntimeException());
        Label label = labelRepository.findById(requestDto.getLabelId()).orElseThrow(() -> new RuntimeException());
        Milestone milestone = milestoneRepository.findById(requestDto.getMileStoneId()).orElseThrow(() -> new RuntimeException());

        Issue newIssue = Issue.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .milestone(milestone)
                .author(author)
                .assignee(assignee)
                .label(label)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        Issue savedIssue = issueRepository.save(newIssue);
        return new NewIssueResponseDto(savedIssue.getId());
    }

    public IssueDetailResponseDto getIssueDetail(Long issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new RuntimeException());
        return IssueDetailResponseDto.of(issue);
    }

    public ModificationResponseDto modifyIssueContent(Long issueId, IssueModificationRequestDto requestDto) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new RuntimeException());

        Milestone milestone = milestoneRepository.findById(requestDto.getMileStoneId()).orElseThrow(() -> new RuntimeException());
        Label label = labelRepository.findById(requestDto.getLabelId()).orElseThrow(() -> new RuntimeException());
        User author = userRepository.findById(requestDto.getAuthorId()).orElseThrow(() -> new RuntimeException());
        User assignee = userRepository.findById(requestDto.getAssigneeId()).orElseThrow(() -> new RuntimeException());

        IssueModificationFieldsDto modificationFieldsDto = IssueModificationFieldsDto.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .milestone(milestone)
                .label(label)
                .author(author)
                .assignee(assignee)
                .build();

        issue.modifyContentsWith(modificationFieldsDto);
        return new ModificationResponseDto(200, "이슈 수정이 완료되었습니다.");
    }
}
