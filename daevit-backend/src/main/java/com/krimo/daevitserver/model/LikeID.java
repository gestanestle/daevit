package com.krimo.daevitserver.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;

@Embeddable
public class LikeID implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "liked_by")
    private User likedBy;

    public LikeID(Post post, User likedBy) {
        this.post = post;
        this.likedBy = likedBy;
    }

    public LikeID() {
    }
}