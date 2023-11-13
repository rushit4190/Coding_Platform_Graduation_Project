package com.NextLeap.Coding_Platform_Graduation_Project.Service.Services;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;

import java.awt.print.Pageable;
import java.util.List;


public interface ProblemService {
    Problem getProblemById(String problemId);

    Problem addProblemToDB(String title, String description, String editorial, String allTestCases, String expectedOutput, String tags, String difficulty);

    List<Problem> getAllProblemsFromDB(Integer pageNo, Integer perPage);


}
