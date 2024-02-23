package com.krimo.daevitserver.service;

import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.repository.LikeRepository;
import com.krimo.daevitserver.repository.PostRepository;
import com.krimo.daevitserver.repository.ShareRepository;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    Post getPost(Long postId);
    void deletePost(Long postId);
    List<Post> getAllPosts(int offset, int count);

}

@Service
@Transactional
class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final ShareRepository shareRepository;

    public PostServiceImpl(PostRepository postRepository, LikeRepository likeRepository, ShareRepository shareRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.shareRepository = shareRepository;
    }

    @Override
    public Post savePost(Post post) {
        logger.info("Saving post: " + post);
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        int likes = likeRepository.countLikes(id);
        int comments = 0;
        int shares = shareRepository.countShares(id);

        post.setLikes(likes);
        post.setComments(comments);
        post.setShares(shares);
        return post;
    }

    @Override
    public List<Post> getAllPosts(int offset, int count) {
        Pageable pageable = PageRequest.of(offset - 1, count);
        return postRepository.findAll(pageable)
                .stream().peek((post)-> {
                    int likes = likeRepository.countLikes(post.getPostId());
                    int comments = 0;
                    int shares = shareRepository.countShares(post.getPostId());
                    post.setLikes(likes);
                    post.setComments(comments);
                    post.setShares(shares);
                }).toList();
    }

    @Override
    public void deletePost(Long postId) {
        logger.info("Deleting post with ID: " + postId);
        postRepository.deleteById(postId);
    }
}


