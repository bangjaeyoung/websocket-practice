package com.websocket.chat.mapper;

import com.websocket.chat.dto.ChatDto;
import com.websocket.chat.entity.Chat;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatMapper {

    Chat chatPostDtoToChat(ChatDto.Post postDto);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    @Mapping(source = "chatRoom.chatRoomId", target = "chatRoomId")
    ChatDto.Response chatToChatResponseDto(Chat chat);
}
