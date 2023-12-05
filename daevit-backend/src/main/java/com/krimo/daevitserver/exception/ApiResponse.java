package com.krimo.daevitserver.exception;

import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
public class ApiResponse {
    private HttpStatus httpStatus;
    private Object data;
    private String message;
    private ZonedDateTime zonedDateTime;

    public static ApiResponse of(HttpStatus httpStatus, Object data, String message) {
        return new ApiResponse(httpStatus, data, message, ZonedDateTime.now(ZoneId.of("Asia/Manila")));
    }

    public ApiResponse() {
    }

    public ApiResponse(HttpStatus httpStatus, Object data, String message, ZonedDateTime zonedDateTime) {
        this.httpStatus = httpStatus;
        this.data = data;
        this.message = message;
        this.zonedDateTime = zonedDateTime;
    }
}
