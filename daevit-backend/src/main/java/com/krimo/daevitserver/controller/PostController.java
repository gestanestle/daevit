package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.dto.PostReadDTO;
import com.krimo.daevitserver.dto.PostWriteDTO;
import com.krimo.daevitserver.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    ResponseEntity<Map<String, Long>> createPost(@RequestBody PostWriteDTO postDTO) {
        logger.info(postDTO.toString());
        Long postId = postService.createPost(postDTO);
        return new ResponseEntity<>(Map.of("post_id", postId), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    ResponseEntity<Object> updatePost(@PathVariable("id") Long id, PostWriteDTO postDTO) {
        postService.updatePost(id, postDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    ResponseEntity<PostReadDTO> getPost(@PathVariable("id") Long postId) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Page<PostReadDTO>> getAllPosts(@RequestParam("pageNo") int pageNo,
                                     @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<Object> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
