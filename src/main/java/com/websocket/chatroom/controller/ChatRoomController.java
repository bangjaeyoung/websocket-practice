package com.websocket.chatroom.controller;

import com.websocket.chatroom.dto.ChatRoomDto;
import com.websocket.chatroom.service.ChatRoomService;
import com.websocket.user.entity.User;
import com.websocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ChatRoomDto.Response> postChatRoom(@Valid @RequestBody ChatRoomDto.Post postDto) {
        //TODO 인증 관련 로직 추가
        User sender = userService.findVerifiedUser(postDto.getSenderId());
        User receiver = userService.findVerifiedUser(postDto.getReceiverId());
        ChatRoomDto.Response response = chatRoomService.createChatRoom(postDto, sender, receiver);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO PATCH

    //TODO GET

    //TODO DELETE
}
