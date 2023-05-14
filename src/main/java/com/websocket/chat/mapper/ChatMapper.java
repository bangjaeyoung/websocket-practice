package com.websocket.chat.mapper;

import com.websocket.chat.dto.ChatDto;
import com.websocket.chat.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mapping(source = "senderId", target = "sender.id")
    @Mapping(source = "receiverId", target = "receiver.id")
    @Mapping(source = "chatRoomId", target = "chatRoom.chatRoomId")
    Chat chatPostDtoToChat(ChatDto.Post postDto);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    @Mapping(source = "chatRoom.chatRoomId", target = "chatRoomId")
    ChatDto.Response chatToChatResponseDto(Chat chat);
}
