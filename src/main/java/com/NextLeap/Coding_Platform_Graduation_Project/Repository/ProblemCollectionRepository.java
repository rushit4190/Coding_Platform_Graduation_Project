package com.NextLeap.Coding_Platform_Graduation_Project.Repository;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.ProblemCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemCollectionRepository extends JpaRepository<ProblemCollection, String> {
}
