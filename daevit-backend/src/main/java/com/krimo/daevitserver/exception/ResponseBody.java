package com.krimo.daevitserver.exception;

public record ResponseBody(
        Integer status,
        Object data,
        String message
) {
}
