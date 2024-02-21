package com.krimo.daevitserver.dto;

import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.User;

import java.time.LocalDateTime;

public class PostDTO extends Post {

    private int likes;
    private int comments;
    private int shares;


    public static PostDTO format(Post post, int likes, int comments, int shares) {
        return new PostDTO(post.getPostId(), post.getTitle(), post.getFlair(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt(),
                post.getAuthor(), likes, comments, shares);
    }

    private PostDTO(Long postId, String title, String flair, String content, LocalDateTime createdAt, LocalDateTime updatedAt, User author, int likes, int comments, int shares) {
        super(postId, title, flair, content, createdAt, updatedAt, author);
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
