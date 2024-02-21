package com.krimo.daevitserver.service;

import com.krimo.daevitserver.exception.ApiException;
import com.krimo.daevitserver.model.LikeID;
import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.PostLike;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.repository.LikeRepository;
import com.krimo.daevitserver.repository.PostRepository;
import com.krimo.daevitserver.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

public interface LikeService {
    void doLike(Long postId, String authId);
    boolean hasLike(Long postId, String authId);
}

@Service
class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public LikeServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void doLike(Long postId, String authId) {

        if (hasLike(postId, authId)) {
            // Unlike the post
            likeRepository.deleteById(getId(postId, authId));
            throw new ApiException("Post unliked.", HttpStatus.OK);
        }

        // Like a post
        likeRepository.save(PostLike.create(getId(postId, authId)));
    }

    @Override
    public boolean hasLike(Long postId, String authId) {
        return likeRepository.existsById(getId(postId, authId));
    }

    private LikeID getId(Long postId, String authId) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.getUserByAuthId(authId).orElseThrow();
        return new LikeID(post, user);
    }
}
