package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.model.Comment;
import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.service.CommentService;
import com.krimo.daevitserver.service.LikeService;
import com.krimo.daevitserver.service.PostService;
import com.krimo.daevitserver.service.ShareService;
import com.krimo.daevitserver.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PostGraphQLHandler {
    
    private final UserService userService;
    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final ShareService shareService;


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

    
    @QueryMapping
    public Boolean hasLike(@Argument Long postId, @Argument String authId) {
        return likeService.hasLike(postId, authId);
    }

    @MutationMapping
    public Long doLike(@Argument Long postId, @Argument String authId) {
        likeService.doLike(postId, authId);
        return postId;
    }

    @QueryMapping
    public Boolean hasShare(@Argument Long postId, @Argument String authId) {
        return shareService.hasShare(postId, authId);
    }

    @MutationMapping
    public Long doShare(@Argument Long postId, @Argument String authId) {
        shareService.doShare(postId, authId);
        return postId;
    }

    @QueryMapping
    public User getUserBy(@Argument String username) { return userService.getByUsername(username); }

    @QueryMapping
    public List<Post> getPostsBy(@Argument String username,  @Argument int offset, @Argument int count) {
        return postService.getPostsBy(username, offset, count);
    }

    @QueryMapping
    public List<Post> getLikedBy(@Argument String username, @Argument int offset, @Argument int count) {
        return postService.getLikedBy(username,  offset, count);
    }

    @QueryMapping
    public List<Comment> getCommentsBy(@Argument String username, @Argument int offset, @Argument int count) {
        return commentService.getCommentsBy(username,  offset, count);
    }

    @QueryMapping
    public List<Post> getSharedBy(@Argument String username, @Argument int offset, @Argument int count) {
        return postService.getSharedBy(username,  offset, count);

    }
}
