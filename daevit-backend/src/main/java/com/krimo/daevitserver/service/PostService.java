package com.krimo.daevitserver.service;

import com.krimo.daevitserver.dto.PostDTO;
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
    PostDTO savePost(Post post);
    PostDTO getPost(Long postId);
    void deletePost(Long postId);
    List<PostDTO> getAllPosts(int offset, int count);

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
    public PostDTO savePost(Post post) {
        logger.info("Saving post: " + post);
        return PostDTO.format(postRepository.save(post), 0, 0, 0);
    }

    @Override
    public PostDTO getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        int likes = likeRepository.countLikes(id);
        int comments = 0;
        int shares = shareRepository.countShares(id);

        return PostDTO.format(post, likes, comments, shares);
    }

    @Override
    public List<PostDTO> getAllPosts(int offset, int count) {
        Pageable pageable = PageRequest.of(offset - 1, count);
        return postRepository.findAll(pageable)
                .stream().map((post)-> {
                    int likes = likeRepository.countLikes(post.getPostId());
                    int comments = 0;
                    int shares = shareRepository.countShares(post.getPostId());
            return PostDTO.format(post, likes, comments, shares);
        }).toList();
    }

    @Override
    public void deletePost(Long postId) {
        logger.info("Deleting post with ID: " + postId);
        postRepository.deleteById(postId);
    }
}


