package com.krimo.daevitserver.dto;

import java.time.LocalDateTime;

public record PostReadDTO(
        Long postId,
        String title,
        String content,
        UserDTO author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

