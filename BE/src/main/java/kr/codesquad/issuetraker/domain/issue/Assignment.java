package kr.codesquad.issuetraker.domain.issue;

import kr.codesquad.issuetraker.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "issue_assignee")
public class Assignment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "issue_assignee_id")
    private Long id;
    @ManyToOne @JoinColumn(name = "issue_id")
    private Issue issue;
    @ManyToOne @JoinColumn(name = "assignee_id")
    private User assignee;

}
