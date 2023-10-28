package com.NextLeap.Coding_Platform_Graduation_Project.Service;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.Problem;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.Submission;
import com.NextLeap.Coding_Platform_Graduation_Project.Model.User;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.SubmissionRepository;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.UserRepository;
import com.NextLeap.Coding_Platform_Graduation_Project.Service.Services.ProblemSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemSubmissionServiceImpl implements ProblemSubmissionService {
    private final WebClient probSubWebClient;
    private final SubmissionRepository submissionRepository;

    private final UserRepository userRepository;
    @Autowired
    public ProblemSubmissionServiceImpl(WebClient probSubWebClient, SubmissionRepository submissionRepository, UserRepository userRepository) {
        this.probSubWebClient = probSubWebClient;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<ResponseEntity<String>> getProblemSubmissionDetails(String token) {

        return probSubWebClient.get()
                .uri("/submissions/{token}{?base64_encoded,fields}", token, true, "*")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                //logError("Client error occurred");
                return Mono.error(new WebClientResponseException
                        (response.statusCode().value(),"Bad Request", null, null, null));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    //logError("Server error occurred");
                    return Mono.error(new WebClientResponseException
                            (response.statusCode().value(),"Bad Request", null, null, null));
                })
                .toEntity(String.class);
    }

    //post body is JSON string with all required parameters
    //wait parameter set true to get submission result instead of token and sending get request.
    @Override
    public Mono<ResponseEntity<String>> postProblemSubmission(String postBody) {

        return probSubWebClient.post()
                .uri("/submissions?base64_encoded=true&wait=true&fields=*")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(postBody)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    //logError("Client error occurred");
                    return response.bodyToMono(String.class)
                            .flatMap(errorResponseBody -> {
                                String errorMessage = "Client error occurred: " + errorResponseBody;
                                return Mono.error(new WebClientResponseException(
                                        response.statusCode().value(),
                                        errorMessage,
                                        response.headers().asHttpHeaders(),
                                        errorResponseBody.getBytes(), // Provide response body as bytes
                                        null
                                ));

                });})
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    //logError("Server error occurred");
                    return Mono.error(new WebClientResponseException
                            (response.statusCode().value(),"Judge0 server error", response.headers().asHttpHeaders(), null, null));
                })
                .toEntity(String.class);
    }

    @Override
    public Submission saveProblemSubmission(String sessionToken, Problem probToSubmit, String submitStatus, String submitCode, Double submitRuntime, String programLang ) {
        User user = userRepository.findBySessionToken(sessionToken).get();
        Submission problemSubmission = new Submission();

        problemSubmission.setUserId(user.getUserId());
        problemSubmission.setProblemId(probToSubmit.getProblemId());
        problemSubmission.setTitle(probToSubmit.getTitle());
        problemSubmission.setStatus(submitStatus);
        problemSubmission.setSubmissionCode(submitCode);
        problemSubmission.setSubmissionRuntime(submitRuntime);
        problemSubmission.setProgrammingLanguage(programLang);
        problemSubmission.setSubmissionTimeStamp(LocalDateTime.now());

        return submissionRepository.save(problemSubmission);
    }

    @Override
    public List<Submission> getProblemSubmissionsForUser(String sessionToken, Problem problemSubmitted) {
        User user = userRepository.findBySessionToken(sessionToken).get();

        List<Submission> submissionList = submissionRepository.findByUserIdAndProblemId(user.getUserId(), problemSubmitted.getProblemId());
        return submissionList;
    }

}
