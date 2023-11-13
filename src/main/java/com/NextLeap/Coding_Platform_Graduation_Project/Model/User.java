package com.NextLeap.Coding_Platform_Graduation_Project.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

// can use Lombok package instead to reduce boilerplate code of constructors and getters and setters
@Entity

@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @UuidGenerator
    private String userId;

    @Column(nullable = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private String sessionToken;

    @Column
    private long sessionCreation;

    @Column
    private String role;

//    @OneToMany(mappedBy = "user")
//    private List<Submission> submissionList;

    public User() {}
    public User(String userId, String email, String firstName, String lastName, String password, String role) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }





    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public long getSessionCreation() {
        return sessionCreation;
    }

    public void setSessionCreation(long sessionCreation) {
        this.sessionCreation = sessionCreation;
    }
}
