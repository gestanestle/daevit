package com.krimo.daevitserver.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST_LIKE")
public class PostLike {

    @EmbeddedId
    private LikeID likeId;
    private LocalDateTime likedAt;

    public PostLike(LikeID likeId, LocalDateTime likedAt) {
        this.likeId = likeId;
        this.likedAt = likedAt;
    }

    public static PostLike create(LikeID likeId) {
        return new PostLike(likeId, LocalDateTime.now());
    }

    public PostLike() {

    }

    public LikeID getLikeId() {
        return likeId;
    }

    public void setLikeId(LikeID likeId) {
        this.likeId = likeId;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }
}
