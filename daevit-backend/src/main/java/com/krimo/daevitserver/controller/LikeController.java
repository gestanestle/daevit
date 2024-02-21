package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.dto.LikeDTO;
import com.krimo.daevitserver.dto.ResponseBody;
import com.krimo.daevitserver.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    ResponseEntity<ResponseBody> doLike(@RequestBody LikeDTO dto) {
        likeService.doLike(dto.postId(), dto.authId());
        return new ResponseEntity<>(new ResponseBody(200, null, "Post liked successfully."), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<ResponseBody> hasLike(@RequestParam("postId") Long postId, @RequestParam("authId") String authId) {
        return new ResponseEntity<>(new ResponseBody(200, likeService.hasLike(postId, authId), null), HttpStatus.OK);
    }
}
