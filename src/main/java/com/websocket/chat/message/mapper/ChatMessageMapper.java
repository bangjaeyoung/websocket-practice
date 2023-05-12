package com.websocket.chat.message.mapper;

import com.websocket.chat.message.dto.ChatMessageDto;
import com.websocket.chat.message.entity.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    ChatMessage dtoToEntity(ChatMessageDto.Request dto);

    ChatMessageDto.Response entityToResponseDto(ChatMessage chatMessage);
}
