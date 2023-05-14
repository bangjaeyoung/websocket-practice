package com.websocket.chat.controller;

import com.websocket.chat.dto.ChatDto;
import com.websocket.chat.service.ChatService;
import com.websocket.chatroom.entity.ChatRoom;
import com.websocket.chatroom.service.ChatRoomService;
import com.websocket.common.entity.dto.MultiResponseDto;
import com.websocket.user.entity.User;
import com.websocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/message")
    public void sendMessage(@Valid ChatDto.Post postDto) {
        ChatRoom chatRoom = chatRoomService.findByChatRoomId(postDto.getChatRoomId());
        User sender = userService.findVerifiedUser(postDto.getSenderId());
        User receiver = userService.findVerifiedUser(postDto.getReceiverId());
        ChatDto.Response response = chatService.createChat(postDto, sender, receiver, chatRoom);
        try {
            simpMessagingTemplate.convertAndSend("/sub/" + postDto.getChatRoomId(), response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/message/{room-id}")
    public ResponseEntity<MultiResponseDto<ChatDto.Response>> getAllChats(@PathVariable("room-id") @Positive Long chatRoomId,
                                      @RequestParam @Positive int page,
                                      @RequestParam @Positive int size) {
        ChatRoom chatRoom = chatRoomService.findByChatRoomId(chatRoomId);
        Page<ChatDto.Response> allChats = chatService.findAllChats(chatRoom, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(allChats), HttpStatus.OK);
    }
}
