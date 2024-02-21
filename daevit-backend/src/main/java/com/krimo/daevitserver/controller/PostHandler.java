package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.dto.PostDTO;
import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.service.PostService;
import com.krimo.daevitserver.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
public class PostHandler {

    private final PostService postService;
    private final UserService userService;

    public PostHandler(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @SchemaMapping
    public User author(Post post) {
        return userService.getUser(post.getAuthor().getAuthId());
    }

    @QueryMapping
    public PostDTO getPostById(@Argument Long postId) {
        return postService.getPost(postId);
    }

    @QueryMapping
    public List<PostDTO> getPosts(@Argument Integer offset, @Argument Integer count) {
        return postService.getAllPosts(offset, count);
    }

    @MutationMapping
    public PostDTO createPost(@Argument String title,
                           @Argument String flair,
                           @Argument String content,
                           @Argument String author) {
        Post post = Post.create(title, flair, content, userService.getUser(author));
        return postService.savePost(post);
    }

    @MutationMapping
    public PostDTO updatePost(@Argument Long postId,
                           @Argument String title,
                           @Argument String flair,
                           @Argument String content) {
        Post post = postService.getPost(postId);
        if (Objects.nonNull(title)) post.setTitle(title);
        if (Objects.nonNull(flair)) post.setFlair(flair);
        if (Objects.nonNull(content)) post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());
        return postService.savePost(post);
    }

    @MutationMapping
    public Long deletePost(@Argument Long postId) {
        postService.deletePost(postId);
        return postId;
    }
}
