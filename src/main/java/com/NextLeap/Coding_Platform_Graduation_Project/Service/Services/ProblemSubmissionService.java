package com.NextLeap.Coding_Platform_Graduation_Project.Service.Services;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.Submission;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ProblemSubmissionService {

    Mono<ResponseEntity<String>> getProblemSubmissionDetails(String token);

    Mono<ResponseEntity<String>> postProblemSubmission(String postBody);

    Submission saveProblemSubmission(String sessionToken, Problem probToSubmit, String submitStatus, String submitCode, Double submitRuntime, String programLang);

    List<Submission> getProblemSubmissionsForUser(String sessionToken, Problem problemSubmitted );
}
