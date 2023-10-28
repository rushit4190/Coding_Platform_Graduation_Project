package com.NextLeap.Coding_Platform_Graduation_Project.Service;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.ProblemRepository;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {


    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public Problem getProblemById(String problemId) throws NoSuchElementException {

        Optional<Problem> prob = problemRepository.findById(problemId);

        if(prob.isPresent()){
            return prob.get();
        }
        else{
            throw new NoSuchElementException();
        }

    }

    @Override
    public Problem addProblemToDB(String title, String description, String editorial, String allTestCases, String expectedOutput, String tags, String difficulty) {
        Problem problemToAdd = new Problem();

        problemToAdd.setTitle(title);
        problemToAdd.setEditorial(editorial);
        problemToAdd.setDescription(description);
        problemToAdd.setAllTestCases(allTestCases);
        problemToAdd.setExpectedOutput(expectedOutput);
        problemToAdd.setTags(tags);
        problemToAdd.setDifficulty(difficulty);

        return problemRepository.save(problemToAdd);
    }

    @Override
    public List<Problem> getAllProblemsFromDB(Integer pageNo, Integer probPerPage) {
        Pageable pageQuery = PageRequest.of(pageNo, probPerPage);
        return problemRepository.findAll(pageQuery).getContent();
    }


}
