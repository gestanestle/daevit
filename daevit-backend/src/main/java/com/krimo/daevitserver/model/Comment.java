package com.krimo.daevitserver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
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

    public static Comment create(Post post, Comment parent, User author, String content) {
        return new Comment(post, parent, author, content, LocalDateTime.now(), LocalDateTime.now());
    }
}
