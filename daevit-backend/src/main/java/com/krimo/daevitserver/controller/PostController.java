package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.dto.PostReadDTO;
import com.krimo.daevitserver.dto.PostWriteDTO;
import com.krimo.daevitserver.dto.ResponseBody;
import com.krimo.daevitserver.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    ResponseEntity<ResponseBody> createPost(@RequestBody PostWriteDTO postDTO) {
        logger.info(postDTO.toString());
        Long postId = postService.createPost(postDTO);
        return new ResponseEntity<>(
            new ResponseBody(201, postId, "Post successfully created."), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    ResponseEntity<ResponseBody> updatePost(@PathVariable("id") Long id, PostWriteDTO postDTO) {
        postService.updatePost(id, postDTO);
        return new ResponseEntity<>(new ResponseBody(200, null, "Post successfully updated."), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    ResponseEntity<ResponseBody> getPost(@PathVariable("id") Long postId) {
        PostReadDTO post = postService.getPost(postId);
        return new ResponseEntity<>(new ResponseBody(200, post, "Post successfully retrieved."), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<ResponseBody> getAllPosts(@RequestParam("pageNo") int pageNo,
                                     @RequestParam("pageSize") int pageSize) {
        List<PostReadDTO> posts = postService.getAllPosts(pageNo, pageSize);
        return new ResponseEntity<>(new ResponseBody(200, posts, "Posts successfully retrieved."), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<ResponseBody> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ResponseBody(200, null, "Post successfully deleted."), HttpStatus.OK);
    }

}
