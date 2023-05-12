package com.websocket.chat.room.service;

import com.websocket.chat.room.entity.ChatRoom;
import com.websocket.chat.room.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom findByChatRoomId(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("CHATROOM_NOT_FOUND"));
    }
}
