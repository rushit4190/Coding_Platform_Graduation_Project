package com.NextLeap.Coding_Platform_Graduation_Project.Controller;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.ProblemCollection;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemCollectionService;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemService;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.SessionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/codingplatform/v1/")
public class ProblemCollectionsController {

    private final ProblemCollectionService problemCollectionService;
    private final SessionManagementService sessionManagementService;
    private final ProblemService problemService;

    @Autowired
    public ProblemCollectionsController(ProblemCollectionService problemCollectionService, SessionManagementService sessionManagementService, ProblemService problemService) {
        this.problemCollectionService = problemCollectionService;
        this.sessionManagementService = sessionManagementService;
        this.problemService = problemService;
    }

    @GetMapping("/problemcollections")
    public ResponseEntity<List<ProblemCollection>> getAllProblemCollections(@RequestHeader(name = "sessionToken", required = false) String sessionId){

        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);

        List<ProblemCollection> problemCollections = problemCollectionService.getProblemCollections();

        if(problemCollections == null){
            return ResponseEntity.noContent().headers(responseHeaders).build();
        }

        return ResponseEntity.ok().headers(responseHeaders).body(problemCollections);
    }

    @GetMapping("/problemcollections/{collectionId}")
    public ResponseEntity<Map<String, Object>> getAllProblemsInCollection(@RequestHeader(name = "sessionToken", required = false) String sessionId, @PathVariable String collectionId){

        Map<String, Object>  response = new HashMap<>();


        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);

        if(!problemCollectionService.validCollectionId(collectionId)){
            response.put("message" ,"Enter valid collectionId");
            return ResponseEntity.badRequest().headers(responseHeaders).body(response);
        }

        List<Problem> problemsInCollection = problemCollectionService.getAllProblemsInCollection(collectionId);

        response.put("Problem_collection", problemsInCollection);
        if(problemsInCollection == null){

            return ResponseEntity.noContent().headers(responseHeaders).build();
        }
        response.put("message" ,"retrieval of problems successful");
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }


    //Used Only for development
    @PostMapping("/problemcollections/{collectionId}")
    public ResponseEntity<String> addProblemToCollection(@PathVariable String collectionId, @RequestParam String problemId){

        if(!problemCollectionService.validCollectionId(collectionId)){
            return ResponseEntity.badRequest().body("Enter valid collectionId");
        }
        try{
            Problem problem = problemService.getProblemById(problemId);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(" Invalid problem id");
        }

        String message = problemCollectionService.saveProblemToCollection(collectionId, problemId);


        return ResponseEntity.ok().body(message);
    }





}
