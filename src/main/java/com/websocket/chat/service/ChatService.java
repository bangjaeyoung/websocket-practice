package com.websocket.chat.service;

import com.websocket.chat.dto.ChatDto;
import com.websocket.chat.entity.Chat;
import com.websocket.chat.mapper.ChatMapper;
import com.websocket.chat.repository.ChatRepository;
import com.websocket.chatroom.entity.ChatRoom;
import com.websocket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;

    @Transactional
    public ChatDto.Response createChat(ChatDto.Post postDto, User sender, User receiver, ChatRoom chatRoom) {
        if (!chatRoom.getSenderId().equals(sender.getId()) && !chatRoom.getReceiverId().equals(sender.getId())) {
            throw new RuntimeException("UNABLE_TO_ACCESS");
        }
        if (!chatRoom.getSenderId().equals(receiver.getId()) && !chatRoom.getReceiverId().equals(receiver.getId())) {
            throw new RuntimeException("UNABLE_TO_ACCESS");
        }
        Chat chat = chatMapper.chatPostDtoToChat(postDto);
        return chatMapper.chatToChatResponseDto(chatRepository.save(chat));
    }
}