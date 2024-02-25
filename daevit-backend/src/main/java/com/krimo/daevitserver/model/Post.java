package com.krimo.daevitserver.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.*;

@Entity
@Table(name = "POST")
@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false)
    private String title;
    @Column
    private String flair;
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne @JoinColumn(name = "author_user_id")
    private User author;
    @Transient private Integer likes;
    @Transient private Integer comments;
    @Transient private Integer shares;

    public Post(String title, String flair, String content, LocalDateTime createdAt, LocalDateTime updatedAt, User author) {
        this.title = title;
        this.flair = flair;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }

    public static Post create(String title, String flair, String content, User author) {
        return new Post(title, flair, content, LocalDateTime.now(), LocalDateTime.now(), author);
    }

}

