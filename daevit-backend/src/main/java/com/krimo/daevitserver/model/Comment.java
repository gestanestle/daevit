package com.krimo.daevitserver.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "comment_by")
    private User commentBy;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(Long commentId, Post post, User commentBy, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.post = post;
        this.commentBy = commentBy;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment(Post post, User commentBy, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.post = post;
        this.commentBy = commentBy;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment() {
    }

    public static Comment create(Post post, User commentBy, String content) {
        return new Comment(post, commentBy, content, LocalDateTime.now(), LocalDateTime.now());
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
