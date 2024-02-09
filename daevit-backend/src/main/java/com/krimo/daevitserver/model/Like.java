package com.krimo.daevitserver.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Like {

    @EmbeddedId
    private LikeID likeId;
    private LocalDateTime timestamp;
    
}

@Embeddable
class LikeID implements Serializable {

    @OneToOne
    private Post post;
    @OneToOne
    private User likedBy;
}