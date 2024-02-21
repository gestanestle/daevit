package com.krimo.daevitserver.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_SHARE")
public class PostShare {

    @EmbeddedId
    private ShareID shareId;
    private LocalDateTime sharedAt;

    public static PostShare create(ShareID shareID) {
        return new PostShare(shareID, LocalDateTime.now());
    }

    public PostShare() {
    }

    public PostShare(ShareID shareID, LocalDateTime sharedAt) {
        this.shareId = shareID;
        this.sharedAt = sharedAt;
    }

    public ShareID getShareID() {
        return shareId;
    }

    public void setShareID(ShareID shareID) {
        this.shareId = shareID;
    }

    public LocalDateTime getSharedAt() {
        return sharedAt;
    }

    public void setSharedAt(LocalDateTime sharedAt) {
        this.sharedAt = sharedAt;
    }
}
