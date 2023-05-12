package com.websocket.chat.message.controller;

import com.websocket.chat.room.entity.ChatRoom;
import com.websocket.chat.message.dto.ChatMessageDto;
import com.websocket.chat.message.service.ChatMessageService;
import com.websocket.chat.room.service.ChatRoomService;
import com.websocket.user.entity.User;
import com.websocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    // 1. 메시지 발행
    @MessageMapping("/message") // "pub/message" 경로로 메시지 발행 시 매핑
    public void sendMessage(ChatMessageDto.Request messageDto) {
        User sender = userService.findVerifiedUser(messageDto.getUserId());
        ChatRoom chatRoom = chatRoomService.findByChatRoomId(messageDto.getChatRoomId());
        ChatMessageDto.Response response = chatMessageService.saveMessage(messageDto, sender, chatRoom);
        simpMessagingTemplate.convertAndSend("/sub/" + messageDto.getChatRoomId(), response);
    }
}
