package com.websocket.chat.service;

import com.websocket.chat.dto.ChatDto;
import com.websocket.chat.entity.Chat;
import com.websocket.chat.mapper.ChatMapper;
import com.websocket.chat.repository.ChatRepository;
import com.websocket.chatroom.entity.ChatRoom;
import com.websocket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
        Long senderId = sender.getId();
        Long receiverId = receiver.getId();
        Long chatRoomSenderId = chatRoom.getSenderId();
        Long chatRoomReceiverId = chatRoom.getReceiverId();
        if (!chatRoomSenderId.equals(senderId) && !chatRoomReceiverId.equals(senderId)) {
            throw new RuntimeException("UNABLE_TO_ACCESS");
        }
        if (!chatRoomSenderId.equals(receiverId) && !chatRoomReceiverId.equals(receiverId)) {
            throw new RuntimeException("UNABLE_TO_ACCESS");
        }
        Chat chat = chatMapper.chatPostDtoToChat(postDto);
        chat.setChatRoom(chatRoom);
        chat.setSender(sender);
        chat.setReceiver(receiver);
        return chatMapper.chatToChatResponseDto(chatRepository.save(chat));
    }

    public Page<ChatDto.Response> findAllChats(ChatRoom chatRoom, int page, int size) {
        //TODO 로그인 기능 추가 시, 토큰과 비교하는 로직 필요
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("chatId").descending());
        return chatRepository.findAllByChatRoom_ChatRoomId(chatRoom, pageRequest)
                .map(chatMapper::chatToChatResponseDto);
    }
}
