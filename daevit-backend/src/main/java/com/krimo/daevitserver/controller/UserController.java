package com.krimo.daevitserver.controller;

import com.krimo.daevitserver.dto.user.Event;
import com.krimo.daevitserver.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    List<Event> events = new ArrayList<>();

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Everything from webhook here user.created, user.updated, user.deleted

    @PostMapping
    ResponseEntity<Object> sendUser(@RequestBody Event event) {
        events.add(event);

        logger.info("Event: " + event.type());
        logger.info("User: " + event.data().username());

        switch (event.type()) {
            case "user.created" -> userService.createUser(event.data());
            case "user.deleted" -> userService.deleteUser(event.data().id());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
