package com.NextLeap.Coding_Platform_Graduation_Project.Repository;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {

    List<Submission> findByUserIdAndProblemId(String userId, String problemId);
}
