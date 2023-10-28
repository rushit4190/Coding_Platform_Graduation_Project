package com.NextLeap.Coding_Platform_Graduation_Project.Service.Services;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.ProblemCollection;

import java.util.List;

public interface ProblemCollectionService {

    List<ProblemCollection> getProblemCollections();

    List<Problem> getAllProblemsInCollection(String collectionId);

    Boolean validCollectionId(String collectionId);

    String saveProblemToCollection(String collectionId, String problemId);


}
