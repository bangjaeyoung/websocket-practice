package com.websocket.chat.message.service;

import com.websocket.chat.room.entity.ChatRoom;
import com.websocket.chat.message.dto.ChatMessageDto;
import com.websocket.chat.message.entity.ChatMessage;
import com.websocket.chat.message.entity.MessageStatus;
import com.websocket.chat.message.mapper.ChatMessageMapper;
import com.websocket.chat.message.repository.ChatMessageRepository;
import com.websocket.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageService {

    private final ChatMessageMapper chatMessageMapper;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessageDto.Response saveMessage(ChatMessageDto.Request dto, User sender, ChatRoom chatRoom) {
        ChatMessageDto.Request messageDto = changeContent(dto, sender.getNickname());
        ChatMessage chatMessage = chatMessageMapper.dtoToEntity(messageDto);
        chatMessage.changeChatRoom(chatRoom);
        ChatMessage savedChatMessage = chatMessageRepository.save(chatMessage);
        return chatMessageMapper.entityToResponseDto(savedChatMessage);
    }

    private ChatMessageDto.Request changeContent(ChatMessageDto.Request messageDto, String nickname) {
        MessageStatus status = messageDto.getStatus();
        if (status.equals(MessageStatus.ENTER)) {
            messageDto.setContent(nickname + "님이 입장하셨습니다.");
        } else if (status.equals(MessageStatus.LEAVE)) {
            messageDto.setContent(nickname + "님이 나가셨습니다.");
        }
        return messageDto;
    }
}
