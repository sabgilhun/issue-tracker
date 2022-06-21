package kr.codesquad.issuetraker.domain.issue;

import kr.codesquad.issuetraker.domain.label.Label;
import kr.codesquad.issuetraker.domain.milestone.Milestone;
import kr.codesquad.issuetraker.domain.user.User;
import kr.codesquad.issuetraker.dto.IssueModificationFieldsDto;
import kr.codesquad.issuetraker.dto.IssueModificationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
    @OneToMany(mappedBy = "issue")
    private final List<Comment> comments = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;
    private boolean isOpened = true;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean isDeleted;


    public void modifyContentsWith(IssueModificationFieldsDto modificationFieldsDto) {
        title = modificationFieldsDto.getTitle();
        description = modificationFieldsDto.getDescription();
        milestone = modificationFieldsDto.getMilestone();
        label = modificationFieldsDto.getLabel();
        author = modificationFieldsDto.getAuthor();
        assignee = modificationFieldsDto.getAssignee();
    }

    public void toggleIsOpened() {
        isOpened = !isOpened;
    }



}
