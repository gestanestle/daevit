package com.krimo.daevitserver.dto;

public record ResponseBody(
        Integer status,
        Object data,
        String message
) {
}
