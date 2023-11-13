package com.NextLeap.Coding_Platform_Graduation_Project.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "problems_collections_mapping")
public class ProblemCollectionMapping {

    @Id
    @UuidGenerator
    private String pc_id;

    @Column(nullable = false)
    private String  collectionId;

    @Column(nullable = false)
    private String problemId;

    public ProblemCollectionMapping() {
    }

    public ProblemCollectionMapping(String pcId, String collectionId, String problemId) {
        pc_id = pcId;
        this.collectionId = collectionId;
        this.problemId = problemId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProbCollectionId() {
        return pc_id;
    }

    public void setProbCollectionId(String probCollectionId) {
        this.pc_id = probCollectionId;
    }
}
