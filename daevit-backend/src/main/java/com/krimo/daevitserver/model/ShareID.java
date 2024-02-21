package com.krimo.daevitserver.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;

@Embeddable
public class ShareID implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_by")
    private User sharedBy;

    public ShareID() {
    }

    public ShareID(Post post, User sharedBy) {
        this.post = post;
        this.sharedBy = sharedBy;
    }
}
