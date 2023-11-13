package com.NextLeap.Coding_Platform_Graduation_Project.Controller;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.Submission;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemService;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemSubmissionService;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.SessionManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/codingplatform/v1/")
public class SubmissionController {


    private final ProblemService problemService;
    private final SessionManagementService sessionManagementService;
    private final ProblemSubmissionService problemSubmissionService;

    @Autowired
    public SubmissionController( ProblemService problemService, SessionManagementService sessionManagementService, ProblemSubmissionService problemSubmissionService) {
        this.problemService = problemService;
        this.sessionManagementService = sessionManagementService;
        this.problemSubmissionService = problemSubmissionService;
    }

    //Request body will have params of source_code, language_id.
    //In post body, we will send JSON of source_code, language_id, stdin, cpu_time_limit. memory_limit can also be added.
    //Base64 encode is kept on.
    @PostMapping("/problems/{problemId}/submit")
    public ResponseEntity<Map<String, Object>> submitProblemSolution(@PathVariable String problemId, @RequestHeader(name = "sessionToken", required = false) String sessionId, @RequestBody Map<String, Object> requestBody) {
        Problem problemToSubmit;
        Map<String, Object> response = new HashMap<>();

//        System.out.println("entered the method");
        try{
            problemToSubmit = problemService.getProblemById(problemId);
        }catch (NoSuchElementException e){
            response.put("message" , "Invalid problem id");
            return ResponseEntity.badRequest().body(response);
        }

        HttpHeaders responseHeaders = sessionManagementService.validate(sessionId);

        //Submit the problem to Judge0
        String sourceCode = (String) requestBody.getOrDefault("source_code", "");
        Integer languageId = (Integer) requestBody.getOrDefault("language_id", null);

        if(sourceCode.isEmpty() || languageId == null){
            response.put("message" , "Source Code is empty or Invalid language id");
            return ResponseEntity.badRequest().headers(responseHeaders).body(response);
        }

        //Encode the string params, put them in  and JSONify them
        // ensure that string to be encoded is backslash-escaped.postman input needs to be in this format
        /// For java source code, ensure that class name is "Main" for Judge 0 to compile

        String sourceCodeEncode = Base64.getEncoder().encodeToString(sourceCode.getBytes());
        String allTestcasesEncode = Base64.getEncoder().encodeToString(problemToSubmit.getAllTestCases().getBytes());
        String expectedOutput = Base64.getEncoder().encodeToString(problemToSubmit.getExpectedOutput().getBytes());

        Map<String, Object> postBodyMap = new HashMap<>();
        postBodyMap.put("source_code", sourceCodeEncode);
        postBodyMap.put("language_id", languageId);
        postBodyMap.put("stdin", allTestcasesEncode);
        if(problemToSubmit.getExpectedOutput() != null){
            postBodyMap.put("expected_output", expectedOutput);
        }

        ObjectMapper mapper = new ObjectMapper();
        String postBody;
        try {
            postBody = mapper.writeValueAsString(postBodyMap);
        } catch (JsonProcessingException e) {
            response.put("message" , e.getMessage());
            return ResponseEntity.internalServerError().headers(responseHeaders).body(response);
        }
//        System.out.println("post body for judge zero ready" + postBody);
        try{
            ResponseEntity<String> submissionResultEntity = problemSubmissionService.postProblemSubmission(postBody).block();

            response.put("message", "Judge0 API call successfull");
            Map<String,Object> submissionDetails= new HashMap<>();

            if(submissionResultEntity != null) {

                String submissionResult = submissionResultEntity.getBody();
                JsonNode rootNode = mapper.readTree(submissionResult);

                //Base64 decode on text attributes
                //Before decoding ensure that the string or text as no line breaks, it has to a continuous encoded string to get decoded
                String submissionOutputDecode = null;
//                System.out.println(rootNode.path("stdout").asText());

                if(rootNode.path("stdout").binaryValue() != null) {
                    byte[] output = Base64.getDecoder().decode(rootNode.path("stdout").asText().replaceAll("\\s", ""));
                    submissionOutputDecode = new String(output);
                }
                String compileOutput = null;
//                System.out.println(rootNode.path("compile_output").asText());
                if(rootNode.path("compile_output").binaryValue() != null){
                    byte[] comOutput = Base64.getUrlDecoder().decode(rootNode.path("compile_output").asText().replaceAll("\\s", ""));
                    compileOutput = new String(comOutput);
                }
                String submitError = null;
                if(rootNode.path("stderr").binaryValue() != null){
                    byte[] err = Base64.getDecoder().decode(rootNode.path("stderr").asText().replaceAll("\\s", ""));
                    submitError = new String(err);
                }

                String submitMessage  = null;
                if(rootNode.path("message").binaryValue() != null){
                    byte[] subMess = Base64.getDecoder().decode(rootNode.path("message").asText().replaceAll("\\s", ""));
                    submitMessage  = new String(subMess);
                }



                Double submitRunTime = rootNode.path("time").asDouble();
                String submitToken = rootNode.path("token").asText();
                String submitStatus = rootNode.path("status").path("description").asText();
                String progLanguage = rootNode.path("language").path("name").asText();

                submissionDetails.put("stdout", submissionOutputDecode);
                submissionDetails.put("token", submitToken);
                submissionDetails.put("stderr", submitError);
                submissionDetails.put("compile_output", compileOutput);
                submissionDetails.put("submit_message", submitMessage);
                submissionDetails.put("memory", rootNode.path("memory").asDouble());

                Submission submission = problemSubmissionService.saveProblemSubmission(responseHeaders.getFirst("sessionToken"), problemToSubmit, submitStatus, sourceCode, submitRunTime, progLanguage);

                response.put("submissionDetails" , submissionDetails);
                response.put("submission", submission);

            }
        } catch (JsonMappingException e) {
            response.put("message", "Json mapping issue: " + e.getMessage());
            return ResponseEntity.internalServerError().headers(responseHeaders).body(response);

        } catch(WebClientResponseException  e){
            response.put("message","Webclient exception : " + e.getMessage() + " + headers" + e.getHeaders() + "response Body +" + e.getResponseBodyAsString());
            return ResponseEntity.internalServerError().headers(responseHeaders).body(response);

        }catch (JsonProcessingException e){
            response.put("message",  "Json processing error " + e.getMessage() );
            return ResponseEntity.internalServerError().headers(responseHeaders).body(response);

        } catch (IOException e) {
            response.put("message",  "Binary value" + e.getMessage() );
            return ResponseEntity.internalServerError().headers(responseHeaders).body(response);

        }catch (IllegalArgumentException e){
            response.put("message",  "Base64 decoding error - " + e.getMessage() + " " + e.getCause());
            return ResponseEntity.internalServerError().headers(responseHeaders).body(response);
        }

        return ResponseEntity.ok().headers(responseHeaders).body(response);

    }

}

