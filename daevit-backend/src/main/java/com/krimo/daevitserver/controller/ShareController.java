package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.dto.PostUserDTO;
import com.krimo.daevitserver.dto.ResponseBody;
import com.krimo.daevitserver.service.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shares")
public class ShareController {

    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping
    ResponseEntity<ResponseBody> doShare(@RequestBody PostUserDTO dto) {
        shareService.doShare(dto.postId(), dto.authId());
        return new ResponseEntity<>(new ResponseBody(200, null, "Post shared successfully."), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<ResponseBody> hasShare(@RequestParam("postId") Long postId, @RequestParam("authId") String authId) {
        return new ResponseEntity<>(new ResponseBody(200, shareService.hasShare(postId, authId), null), HttpStatus.OK);
    }
}
