package com.krimo.daevitserver.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    @Column(nullable = false)
    private String title;
    @Column
    private String flair;
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne @JoinColumn(name = "author_userId")
    private User author;

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

    public Post() {
    }

    public Post(Long postId, String title, String flair, String content, LocalDateTime createdAt, LocalDateTime updatedAt, User author) {
        this.postId = postId;
        this.title = title;
        this.flair = flair;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getFlair() {
        return flair;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFlair(String flair) {
        this.flair = flair;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post [" +
                " postId: " + postId +
                ", title: '" + title + '\'' +
                ", flair: '" + flair + '\'' +
                ", content: '" + content + '\'' +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                ", author: " + author +
                " ]";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Post post)) return false;
        return Objects.equals(postId, post.postId) && Objects.equals(title, post.title) && Objects.equals(flair, post.flair) && Objects.equals(content, post.content) && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt) && Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, title, flair, content, createdAt, updatedAt, author);
    }
}

