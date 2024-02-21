package com.krimo.daevitserver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "DAEVIT_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String authId;
    private String username;
    private String email;
    private String birthdate;
    private String lastName;
    private String firstName;
    private String profileImageURL;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String authId, String username, String email, String birthdate, String lastName, String firstName, String profileImageURL, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.authId = authId;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileImageURL = profileImageURL;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
    }

    public User(Long userId, String authId, String username, String email, String birthdate, String lastName, String firstName, String profileImageURL, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.authId = authId;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileImageURL = profileImageURL;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User {" +
                "userId=" + userId +
                ", authId='" + authId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(userId, user.userId) && Objects.equals(authId, user.authId) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(birthdate, user.birthdate) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(profileImageURL, user.profileImageURL) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, authId, username, email, birthdate, lastName, firstName, profileImageURL, createdAt, updatedAt);
    }
}
