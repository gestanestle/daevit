package com.krimo.daevitserver.service;

import com.krimo.daevitserver.exception.ApiException;
import com.krimo.daevitserver.model.*;
import com.krimo.daevitserver.repository.PostRepository;
import com.krimo.daevitserver.repository.ShareRepository;
import com.krimo.daevitserver.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

public interface ShareService {

    void doShare(Long postId, String authId);
    boolean hasShare(Long postId, String authId);
}

@Service
class ShareServiceImpl implements ShareService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    public ShareServiceImpl(PostRepository postRepository, UserRepository userRepository, ShareRepository shareRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.shareRepository = shareRepository;
    }

    @Override
    public void doShare(Long postId, String authId) {
        if (hasShare(postId, authId)) {
            // Un-share the post
            shareRepository.deleteById(getId(postId, authId));
            throw new ApiException("Post unshared.", HttpStatus.OK);
        }

        // Share a post
        shareRepository.save(PostShare.create(getId(postId, authId)));
    }

    @Override
    public boolean hasShare(Long postId, String authId) {
        return shareRepository.existsById(getId(postId, authId));
    }

    private ShareID getId(Long postId, String authId) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.getUserByAuthId(authId).orElseThrow();
        return new ShareID(post, user);
    }
}
