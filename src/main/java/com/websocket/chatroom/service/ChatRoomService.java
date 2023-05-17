package com.websocket.chatroom.service;

import com.websocket.chatroom.dto.ChatRoomDto;
import com.websocket.chatroom.mapper.ChatRoomMapper;
import com.websocket.chatroom.entity.ChatRoom;
import com.websocket.chatroom.repository.ChatRoomRepository;
import com.websocket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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


    @Transactional
    public ChatRoomDto.Response updateChatRoom(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = findChatRoomByChatRoomId(chatRoomId);
        if (chatRoom.getLeaverId() == null) {
            chatRoom.setLeaverId(userId);
        } else {
            chatRoom.setLeaverId(null);
        }
        return chatRoomMapper.chatRoomToChatRoomResponseDto(chatRoom);
    }

    public List<ChatRoomDto.Response> findChatRoomsByUserId(Long userId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsBySenderIdOrReceiverId(userId);
        return chatRooms.stream()
                .map(chatRoomMapper::chatRoomToChatRoomResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = findChatRoomByChatRoomId(chatRoomId);
        if (chatRoom.getLeaverId() == null) {
            throw new RuntimeException("UNABLE_TO_DELETE");
        }
        chatRoomRepository.delete(chatRoom);
    }

    public ChatRoom findChatRoomByChatRoomId(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("CHATROOM_NOT_FOUND"));
    }

    private void checkChatRoomAlreadyExist(User sender, User receiver) {
        Long senderId = sender.getId();
        Long receiverId = receiver.getId();
        ChatRoom chatRoom = chatRoomRepository.findChatRoomBySenderAndReceiverIds(senderId, receiverId);
        if (chatRoom != null) {
            throw new RuntimeException("CHATROOM_ALREADY_EXISTS");
        }
    }
}
