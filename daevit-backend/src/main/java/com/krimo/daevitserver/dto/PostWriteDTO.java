package com.krimo.daevitserver.dto;

import java.time.LocalDateTime;

public record PostWriteDTO(
        Long postId,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
} 
