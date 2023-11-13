package com.NextLeap.Coding_Platform_Graduation_Project.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ProblemSubmissionConfig {

    private String judge0RapidAPIUrl = "https://judge0-ce.p.rapidapi.com/";
    private String RapidAPIKey = "6d198f4682mshae0ae193f2aafafp1897cejsnfbb56c1fde1d";
    private String RapidAPIHost = "judge0-ce.p.rapidapi.com";
    @Bean
    public WebClient probSubmWebClient(){

        WebClient webClient = WebClient.builder()
                .baseUrl(judge0RapidAPIUrl)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-RapidAPI-Key", RapidAPIKey)
                .defaultHeader("X-RapidAPI-Host", RapidAPIHost)
                .build();

        return webClient;
    }

}
