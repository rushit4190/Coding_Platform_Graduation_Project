package com.NextLeap.Coding_Platform_Graduation_Project.Repository;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.ProblemCollectionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemCollectionMappingRepository extends JpaRepository<ProblemCollectionMapping, String> {

    List<ProblemCollectionMapping> findAllByCollectionId(String collectionId);
}
