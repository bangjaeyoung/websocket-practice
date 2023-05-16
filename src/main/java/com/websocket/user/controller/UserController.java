package com.websocket.user.controller;

import com.websocket.user.dto.UserDto;
import com.websocket.user.entity.User;
import com.websocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> postUser(@RequestBody UserDto.Post postDto) {
        User user = userService.createUser(postDto);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }
}
