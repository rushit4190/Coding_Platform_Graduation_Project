package com.NextLeap.Coding_Platform_Graduation_Project.Controller;


import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.Submission;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemService;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemSubmissionService;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.SessionManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/codingplatform/v1/")
public class ProblemController {
    private final ProblemService problemService;
    private final SessionManagementService sessionManagementService;
    private final ProblemSubmissionService problemSubmissionService;
    @Autowired
    public ProblemController(ProblemService problemService, SessionManagementService sessionManagementService, ProblemSubmissionService problemSubmissionService) {
        this.problemService = problemService;
        this.sessionManagementService = sessionManagementService;
        this.problemSubmissionService = problemSubmissionService;
    }

    @GetMapping("/problems/{problemId}/description")
    public ResponseEntity<String> getProblemDescription(@PathVariable String problemId, @RequestHeader(name = "sessionToken", required = false) String sessionId){
        String description;
//        System.out.println("entered the method");
        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);
        try{
            description = problemService.getProblemById(problemId).getDescription();
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Invalid problem id");
        }
//        System.out.println("Description fetched : " + description);



        return ResponseEntity.ok().headers(responseHeaders).body(description);
    }

    @GetMapping("/problems/{problemId}/editorial")
    public ResponseEntity<String> getProblemEditorial(@PathVariable String problemId, @RequestHeader(name = "sessionToken", required = false) String sessionId){
        String editorial;
        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);
        try{
            editorial = problemService.getProblemById(problemId).getEditorial();
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Invalid problem id");
        }


        return ResponseEntity.ok().headers(responseHeaders).body(editorial);
    }

    @GetMapping("/problems/{problemId}/submissions")
    public ResponseEntity<Map<String, Object>> getProblemSubmissions(@PathVariable String problemId, @RequestHeader(name = "sessionToken", required = false) String sessionId){
        Problem problemSubmitted;
        Map<String, Object> response = new HashMap<>();
        try{
            problemSubmitted = problemService.getProblemById(problemId);
        }catch (NoSuchElementException e){
            response.put("message", e.getMessage() + " Invalid problem id");

            return ResponseEntity.badRequest().body(response);
        }
        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);

        if(sessionId == null){
            response.put("message", " No submissions yet");
            response.put("submission", new ArrayList<Submission>());
            return ResponseEntity.ok().headers(responseHeaders).body(response);
        }
        List<Submission> submissionList = problemSubmissionService.getProblemSubmissionsForUser(responseHeaders.getFirst("sessionToken"), problemSubmitted);
        response.put("message", " Submissions retrieved successfully");
        response.put("submission", submissionList);
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }
    @GetMapping("/problems/all")
    public ResponseEntity<List<Problem>> getAllProblems(@RequestHeader(name = "sessionToken", required = false) String sessionId, @RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "10") Integer pageSize){
        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);
        List<Problem> allProblems = problemService.getAllProblemsFromDB(pageNo, pageSize);

        return ResponseEntity.ok().headers(responseHeaders).body(allProblems);
    }

    //This API is only for development
    @PostMapping("/addproblem")
    public ResponseEntity<Problem> addProblem(@RequestBody Map<String, String> requestBody){
        Problem probAdded = problemService.addProblemToDB(requestBody.getOrDefault("title", ""), requestBody.getOrDefault("description", ""), requestBody.getOrDefault("editorial", ""), requestBody.getOrDefault("allTestCases", ""), requestBody.getOrDefault("expectedOutput", ""), requestBody.getOrDefault("tags", ""), requestBody.getOrDefault("difficulty", ""));
        return ResponseEntity.ok().body(probAdded);
    }



    @GetMapping("/")
    public String hello(){
//        System.out.println("Printing Hello on console");
        return "Hello Checking connection!";
    }

    @PostMapping("/sample_post")
    public ResponseEntity<String> checkPost(){
//        System.out.println("Printing Hello on console");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).body("Sample post submitted");
    }

}

