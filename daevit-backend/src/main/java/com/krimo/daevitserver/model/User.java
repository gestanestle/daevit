package com.krimo.daevitserver.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.*;


@Entity
@Table(name = "DAEVIT_USER")
@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
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
}
