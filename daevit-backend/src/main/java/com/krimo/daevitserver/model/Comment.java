package com.krimo.daevitserver.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "parent")
    private Comment parent;
    @ManyToOne
    @JoinColumn(name = "author_user_id")
    private User author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Transient  private Integer likes;

    public Comment(Long commentId, Post post, Comment parent, User author, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.post = post;
        this.parent = parent;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment(Post post, Comment parent, User author, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.post = post;
        this.parent = parent;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment(Long commentId, Post post, Comment parent, User author, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Integer likes) {
        this.commentId = commentId;
        this.post = post;
        this.parent = parent;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
    }

    public Comment() {
    }

    public static Comment create(Post post, Comment parentComment, User author, String content) {
        return new Comment(post, parentComment, author, content, LocalDateTime.now(), LocalDateTime.now());
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
