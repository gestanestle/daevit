package com.krimo.daevitserver.dto;

public record UserDTO(
    String authId,
    String username,
    String lastName,
    String firstName,
    String profileImageURL
) {
} 