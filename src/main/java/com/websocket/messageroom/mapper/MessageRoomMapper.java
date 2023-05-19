package com.websocket.messageroom.mapper;

import com.websocket.messageroom.dto.MessageRoomDto;
import com.websocket.messageroom.entity.MessageRoom;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageRoomMapper {
    MessageRoom messageRoomPostDtoToMessageRoom(MessageRoomDto.Post postDto);

    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.nickname")
    @Mapping(target = "receiverId", source = "receiver.id")
    @Mapping(target = "receiverName", source = "receiver.nickname")
    MessageRoomDto.Response messageRoomToMessageRoomResponseDto(MessageRoom messageRoom);

    MessageRoomDto.SimpleResponse messageRoomToMessageRoomSimpleResponseDto(MessageRoom messageRoom);
}
