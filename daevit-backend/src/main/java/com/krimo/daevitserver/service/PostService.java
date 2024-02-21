package com.krimo.daevitserver.service;

import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.repository.PostRepository;
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

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(Post post) {
        logger.info("Saving post: " + post);
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Post> getAllPosts(int offset, int count) {
        Pageable pageable = PageRequest.of(offset - 1, count);
        return postRepository.findAll(pageable).stream().toList();
    }

    @Override
    public void deletePost(Long postId) {
        logger.info("Deleting post with ID: " + postId);
        postRepository.deleteById(postId);
    }
}


