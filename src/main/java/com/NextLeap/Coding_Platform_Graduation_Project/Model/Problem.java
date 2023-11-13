package com.NextLeap.Coding_Platform_Graduation_Project.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;



// can use Lombok package instead to reduce boilerplate code of constructors and getters and setters
@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @UuidGenerator
    private String problemId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")               //Can use @Lob
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")               //Can use @Lob
    private String editorial;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String allTestCases;

    @Column(columnDefinition = "TEXT")
    private String customTestCases;       //Convert String array into JSON string

    @Column(columnDefinition = "TEXT")
    private String expectedOutput;

    @Column(columnDefinition = "TEXT")
    private String customTestOutput;
    @Column
    private String tags;                  //Convert String array into JSON string

    @Column(nullable = false)
    private String difficulty;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "problem")
//    private List<Submission> allSubmissions;

//    @ManyToMany(mappedBy = "problemCollectionList")
//    List<ProblemCollection> collections;

    public Problem() {
    }

    public Problem(String problemId, String title, String description, String editorial, String allTestCases, String customTestCases, String tags, String difficulty) {
        this.problemId = problemId;
        this.title = title;
        this.description = description;
        this.editorial = editorial;
        this.allTestCases = allTestCases;
        this.customTestCases = customTestCases;
        this.tags = tags;
        this.difficulty = difficulty;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAllTestCases() {
        return allTestCases;
    }

    public void setAllTestCases(String allTestCases) {
        this.allTestCases = allTestCases;
    }

    public String getCustomTestCases() {
        return customTestCases;
    }

    public void setCustomTestCases(String customTestCases) {
        this.customTestCases = customTestCases;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public String getCustomTestOutput() {
        return customTestOutput;
    }

    public void setCustomTestOutput(String customTestOutput) {
        this.customTestOutput = customTestOutput;
    }
}
