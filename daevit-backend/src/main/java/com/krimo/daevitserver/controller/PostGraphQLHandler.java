package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.model.Comment;
import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.service.CommentService;
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
public class PostGraphQLHandler {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    public PostGraphQLHandler(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @SchemaMapping
    public User author(Post post) {
        return userService.getUser(post.getAuthor().getAuthId());
    }

    @QueryMapping
    public Post getPostById(@Argument Long postId) {
        return postService.getPost(postId);
    }

    @QueryMapping
    public List<Post> getPosts(@Argument Integer offset, @Argument Integer count) {
        return postService.getAllPosts(offset, count);
    }

    @MutationMapping
    public Post createPost(@Argument String title,
                           @Argument String flair,
                           @Argument String content,
                           @Argument String author) {
        Post post = Post.create(title, flair, content, userService.getUser(author));
        return postService.savePost(post);
    }

    @MutationMapping
    public Post updatePost(@Argument Long postId,
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

    @QueryMapping
    public List<Comment> getBaseComments(@Argument Long postId, @Argument Integer offset, @Argument Integer count) {
        return commentService.getBaseComments(postId, offset, count);
    }

    @QueryMapping
    public List<Comment> getChildComments(@Argument Long parentId, @Argument Integer offset, @Argument Integer count) {
        return commentService.getChildComments(parentId, offset, count);
    }

    @MutationMapping
    public Comment createComment(@Argument Long postId, @Argument Long parentId,
                                 @Argument String content, @Argument String author) {

        Post post = postService.getPost(postId);
        Comment parent = parentId == null ? null : commentService.getComment(parentId);
        User user = userService.getUser(author);
        Comment comment = Comment.create(post, parent, user, content);
        return commentService.saveComment(comment);
    }

    @MutationMapping
    public Comment updateComment(@Argument Long commentId, @Argument String content) {
        if (commentId == null || content == null) return null;
        Comment comment = commentService.getComment(commentId);
        comment.setContent(content);
        comment.setUpdatedAt(LocalDateTime.now());
        return commentService.saveComment(comment);
    }

    @MutationMapping
    public Long deleteComment(@Argument Long commentId) {
        commentService.deleteComment(commentId);
        return commentId;
    }
}