package com.NextLeap.Coding_Platform_Graduation_Project.Service;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.ProblemCollection;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.ProblemCollectionMapping;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.ProblemCollectionMappingRepository;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.ProblemCollectionRepository;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.ProblemRepository;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemCollectionServiceImpl implements ProblemCollectionService {

    private final ProblemCollectionRepository problemCollectionRepository;
    private final ProblemCollectionMappingRepository problemCollectionMappingRepository;
    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemCollectionServiceImpl(ProblemCollectionRepository problemCollectionRepository, ProblemCollectionMappingRepository problemCollectionMappingRepository, ProblemRepository problemRepository) {
        this.problemCollectionRepository = problemCollectionRepository;
        this.problemCollectionMappingRepository = problemCollectionMappingRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public List<ProblemCollection> getProblemCollections() {
        List<ProblemCollection> problemCollectionList = problemCollectionRepository.findAll();
        return problemCollectionList.isEmpty() ? null : problemCollectionList;
    }

    @Override
    public List<Problem> getAllProblemsInCollection(String collectionId) {
        List<ProblemCollectionMapping> allProblemColMap = problemCollectionMappingRepository.findAllByCollectionId(collectionId);
        List<Problem> problemList = new ArrayList<>();
        for(ProblemCollectionMapping problemCollectionMapping : allProblemColMap){
            problemList.add(problemRepository.findById(problemCollectionMapping.getProblemId()).get());
        }
        return problemList;
    }

    @Override
    public Boolean validCollectionId(String collectionId) {
        return problemCollectionRepository.existsById(collectionId);
    }

    @Override
    public String saveProblemToCollection(String collectionId, String problemId) {
        ProblemCollectionMapping problemCollectionMapping = new ProblemCollectionMapping();
        problemCollectionMapping.setCollectionId(collectionId);
        problemCollectionMapping.setProblemId(problemId);
        problemCollectionMappingRepository.save(problemCollectionMapping);
        return "Successfull";
    }

}
