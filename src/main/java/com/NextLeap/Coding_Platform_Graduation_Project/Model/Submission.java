package com.NextLeap.Coding_Platform_Graduation_Project.Model;


import jakarta.persistence.*;

import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDateTime;


@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @UuidGenerator
    private String submissionId;

    @Column(nullable = false)
    private String problemId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime submissionTimeStamp;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "TEXT")         // can use @Lob
    private String submissionCode;

    @Column(nullable = false)
    private String programmingLanguage;

    @Column(nullable = false)
    private double submissionRuntime;


//    @ManyToOne
//    @JoinColumn(name = "problemId")
//    private Problem problem;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;

    public Submission() {
    }

    public Submission(String submissionId, String problemId, String title, String status, LocalDateTime submissionTimeStamp, String userId, String submissionCode, String programmingLanguage) {
        this.submissionId = submissionId;
        this.problemId = problemId;
        this.title = title;
        this.status = status;
        this.submissionTimeStamp = submissionTimeStamp;
        this.userId = userId;
        this.submissionCode = submissionCode;
        this.programmingLanguage = programmingLanguage;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionTimeStamp() {
        return submissionTimeStamp;
    }

    public void setSubmissionTimeStamp(LocalDateTime submissionTimeStamp) {
        this.submissionTimeStamp = submissionTimeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubmissionCode() {
        return submissionCode;
    }

    public void setSubmissionCode(String submissionCode) {
        this.submissionCode = submissionCode;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public double getSubmissionRuntime() {
        return submissionRuntime;
    }

    public void setSubmissionRuntime(double submissionRuntime) {
        this.submissionRuntime = submissionRuntime;
    }
}
