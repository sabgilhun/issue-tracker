package kr.codesquad.issuetraker.controller;

import kr.codesquad.issuetraker.dto.CreateIssueResponseDto;
import kr.codesquad.issuetraker.dto.IssueListResponseDto;
import kr.codesquad.issuetraker.dto.NewIssueRequestDto;
import kr.codesquad.issuetraker.sevice.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("issues")
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueListResponseDto>> loadAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @PostMapping
    public ResponseEntity<CreateIssueResponseDto> createIssue(@ModelAttribute NewIssueRequestDto requestDto) {
        return ResponseEntity.ok(issueService.createIssue(requestDto));
    }
}
