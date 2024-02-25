package com.krimo.daevitserver.model;

import java.util.List;

public record UserT(
    User user;
    List<Post> posts,
    List<Post> likes,
    List<Comment> comments;
    List<Post> shares;
) {
}