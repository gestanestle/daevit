package com.krimo.daevitserver.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

class PostControllerTest extends BaseConfig {

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void getPost() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/v1/posts")
                .then().statusCode(200);
    }

    @Test
    void getAllPosts() {
    }

    @Test
    void deletePost() {
    }
}