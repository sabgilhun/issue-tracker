package kr.codesquad.issuetraker.domain.issue;

import kr.codesquad.issuetraker.domain.label.Label;
import kr.codesquad.issuetraker.domain.milestone.Milestone;
import kr.codesquad.issuetraker.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
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
    @OneToMany(mappedBy = "issue")
    private List<Assignment> assignments = new ArrayList<>();
    @OneToMany(mappedBy = "issue")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne @JoinColumn(name = "label_id")
    private Label label;
    private boolean isOpened;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
