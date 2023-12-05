package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.DaevitServerApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = DaevitServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseConfig {

    @LocalServerPort
    private Integer port;
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

}
