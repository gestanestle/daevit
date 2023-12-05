package com.krimo.daevitserver.service;

import com.krimo.daevitserver.dto.PostReadDTO;
import com.krimo.daevitserver.dto.PostWriteDTO;
import com.krimo.daevitserver.dto.UserDTO;
import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.repository.PostRepository;
import com.krimo.daevitserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

public interface PostService {
    Long createPost(PostWriteDTO postDTO);
    PostReadDTO getPost(Long id);
    Page<PostReadDTO> getAllPosts(int pageNo, int pageSize);
    void updatePost(Long postId, PostWriteDTO postDTO);
    void deletePost(Long postId);

}

@Service
@Transactional
class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Long createPost(PostWriteDTO postDTO) {
        User user = userRepository.getUserByAuthId(postDTO.author()).orElseThrow();
        Post post = postRepository.save(Post.of(postDTO.title(), postDTO.content(), user));
        logger.info("Creating post..." + post);
        return post.getPostId();
    }

    @Override
    public PostReadDTO getPost(Long id) {
        return postRepository.findById(id).map(this::mapToPostDTO).orElseThrow();
    }

    @Override
    public Page<PostReadDTO> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return postRepository.findAll(pageable).map(this::mapToPostDTO);
    }

    @Override
    public void updatePost(Long postId, PostWriteDTO postDTO) {
        Post post = postRepository.findById(postId).orElseThrow();
        if (Objects.isNull(postDTO.content())) return;
        post.setContent(postDTO.content());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    private PostReadDTO mapToPostDTO(Post post) {
        return new PostReadDTO(post.getPostId(), post.getTitle(), post.getContent(),
                new UserDTO(post.getAuthor().getAuthId(), post.getAuthor().getUsername(), 
                    post.getAuthor().getLastName(), post.getAuthor().getFirstName(), 
                    post.getAuthor().getProfileImageURL()), 
                post.getCreatedAt(), post.getUpdatedAt());
    }
}


