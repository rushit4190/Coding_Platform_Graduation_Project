package com.NextLeap.Coding_Platform_Graduation_Project.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name = "problem_collections")
public class ProblemCollection {

    @Id
    @UuidGenerator
    private String collectionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

//    @ManyToMany
//    @JoinTable(
//            name = "problems_collections_mapping",
//            joinColumns = @JoinColumn(name = "collectionId"),
//            inverseJoinColumns = @JoinColumn(name = "problemId")
//    )
//    private List<Problem> problemCollectionList;
    public ProblemCollection() {
    }

    public ProblemCollection(String collectionId, String title, String description, byte[] image) {
        this.collectionId = collectionId;
        this.title = title;
        this.description = description;
        this.image = image;
    }


    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
