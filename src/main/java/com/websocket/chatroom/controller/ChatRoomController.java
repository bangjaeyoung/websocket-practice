package com.websocket.chatroom.controller;

import com.websocket.chatroom.dto.ChatRoomDto;
import com.websocket.chatroom.service.ChatRoomService;
import com.websocket.common.entity.dto.ListResponseDto;
import com.websocket.user.entity.User;
import com.websocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

    //TODO HTTP 메서드별 인증 관련 로직 추가

    @PostMapping
    public ResponseEntity<ChatRoomDto.Response> postChatRoom(@Valid @RequestBody ChatRoomDto.Post postDto) {
        User sender = userService.findVerifiedUser(postDto.getSenderId());
        User receiver = userService.findVerifiedUser(postDto.getReceiverId());
        ChatRoomDto.Response response = chatRoomService.createChatRoom(postDto, sender, receiver);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //채팅방 나간 유저 기록, 불필요시 제거
    @PatchMapping("/{chatroom-id}")
    public ResponseEntity<ChatRoomDto.Response> patchChatRoom(@PathVariable("chatroom-id") @Positive Long chatRoomId,
                                                              @RequestBody @Valid ChatRoomDto.Patch patchDto) {
        ChatRoomDto.Response response = chatRoomService.updateChatRoom(chatRoomId, patchDto.getUserId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<List<ChatRoomDto.Response>> getChatRoomsByUserId(@PathVariable("user-id") @Positive Long userId) {
        List<ChatRoomDto.Response> response = chatRoomService.findChatRoomsByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{chatroom-id}")
    public ResponseEntity<HttpStatus> deleteChatRoom(@PathVariable("chatroom-id") @Positive Long chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
