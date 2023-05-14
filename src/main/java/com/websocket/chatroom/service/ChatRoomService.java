package com.websocket.chatroom.service;

import com.websocket.chatroom.dto.ChatRoomDto;
import com.websocket.chatroom.entity.ChatRoom;
import com.websocket.chatroom.mapper.ChatRoomMapper;
import com.websocket.chatroom.repository.ChatRoomRepository;
import com.websocket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatRoomDto.Response createChatRoom(ChatRoomDto.Post postDto, User sender, User receiver) {
        checkChatRoomAlreadyExist(sender, receiver);
        ChatRoom chatRoom = chatRoomMapper.chatRoomPostDtoToChatRoom(postDto);
        chatRoom.setSender(sender);
        chatRoom.setReceiver(receiver);
        return chatRoomMapper.chatRoomToChatRoomResponseDto(chatRoomRepository.save(chatRoom));
    }

    private void checkChatRoomAlreadyExist(User sender, User receiver) {
        Long senderId = sender.getId();
        Long receiverId = receiver.getId();
        ChatRoom chatRoom = chatRoomRepository.findChatRoom(senderId, receiverId);
        if (chatRoom != null) {
            throw new RuntimeException("CHATROOM_ALREADY_EXISTS");
        }
    }

    public ChatRoom findByChatRoomId(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("CHATROOM_NOT_FOUND"));
    }
}
