package com.websocket.message.controller;

import com.websocket.message.dto.MessageDto;
import com.websocket.messageroom.entity.MessageRoom;
import com.websocket.messageroom.service.MessageRoomService;
import com.websocket.message.service.MessageService;
import com.websocket.user.entity.User;
import com.websocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {

    private final MessageService messageService;
    private final MessageRoomService messageRoomService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<MessageDto.Response> postMessage(@Valid @RequestBody MessageDto.Post postDto) {
        MessageRoom messageRoom = messageRoomService.findVerifiedMessageRoom(postDto.getMessageRoomId());
        User sender = userService.findVerifiedUser(postDto.getSenderId());  // email
        User receiver = userService.findVerifiedUser(postDto.getReceiverId());
        MessageDto.Response response = messageService.createMessage(postDto, messageRoom, sender, receiver);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @MessageMapping("/chats/{roomId}")
    public void handleChatMessage(@DestinationVariable Long roomId,
                                  @Valid @RequestBody MessageDto.Post postDto) {
        MessageRoom messageRoom = messageRoomService.findVerifiedMessageRoom(postDto.getMessageRoomId());
        User sender = userService.findVerifiedUser(postDto.getSenderId());  // email
        User receiver = userService.findVerifiedUser(postDto.getReceiverId());
        messageService.createMessage(postDto, messageRoom, sender, receiver);
    }
}
