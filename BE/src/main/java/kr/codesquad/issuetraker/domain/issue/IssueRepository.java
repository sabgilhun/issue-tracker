package kr.codesquad.issuetraker.domain.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query(value = "SELECT IS FROM Issue as IS where IS.isDeleted = FALSE")
    public List<Issue> findAllAvailableIssues();
}
