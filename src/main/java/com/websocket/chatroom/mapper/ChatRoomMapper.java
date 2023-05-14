package com.websocket.chatroom.mapper;

import com.websocket.chatroom.dto.ChatRoomDto;
import com.websocket.chatroom.entity.ChatRoom;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatRoomMapper {

    ChatRoom chatRoomPostDtoToChatRoom(ChatRoomDto.Post chatRoomPostDto);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    ChatRoomDto.Response chatRoomToChatRoomResponseDto(ChatRoom chatRoom);
}
