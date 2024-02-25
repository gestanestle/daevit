package com.krimo.daevitserver.service;

import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.repository.CommentRepository;
import com.krimo.daevitserver.repository.LikeRepository;
import com.krimo.daevitserver.repository.PostRepository;
import com.krimo.daevitserver.repository.ShareRepository;
import com.krimo.daevitserver.utils.Pg;

import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    Post getPost(Long postId);
    void deletePost(Long postId);
    List<Post> getAllPosts(int offset, int count);
    List<Post> getPostsBy(String username, int offset, int count);
    List<Post> getLikedBy(String username, int offset, int count);
    List<Post> getSharedBy(String username, int offset, int count);
}

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ShareRepository shareRepository;

    @Override
    public Post savePost(Post post) {
        log.info("Saving post: " + post);
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        int likes = likeRepository.countLikes(id);
        int comments = commentRepository.countComments(id);
        int shares = shareRepository.countShares(id);

        post.setLikes(likes);
        post.setComments(comments);
        post.setShares(shares);
        return post;
    }

    @Override
    public void deletePost(Long postId) {
        log.info("Deleting post with ID: " + postId);
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getAllPosts(int offset, int count) {
        return postRepository.findAll(Pg.find(offset, count))
                .stream().peek((post)-> setLCS(post)).toList();
    }

    @Override
    public List<Post> getPostsBy(String username, int offset, int count) {
        return postRepository.getPostsBy(username, Pg.find(offset, count))
                .stream().peek((post)-> setLCS(post)).toList();

    }

    @Override
    public List<Post> getLikedBy(String username, int offset, int count) {
        return likeRepository.getLikedBy(username, Pg.find(offset, count))
                .stream().peek((post)-> setLCS(post)).toList();
    }


    @Override
    public List<Post> getSharedBy(String username, int offset, int count) {
         return shareRepository.getSharedBy(username, Pg.find(offset, count))
                .stream().peek((post)-> setLCS(post)).toList();
    }

    private Post setLCS(Post post) {
        int likes = likeRepository.countLikes(post.getPostId());
        int comments = commentRepository.countComments(post.getPostId());
        int shares = shareRepository.countShares(post.getPostId());
        post.setLikes(likes);
        post.setComments(comments);
        post.setShares(shares);
        return post;
    }

}


