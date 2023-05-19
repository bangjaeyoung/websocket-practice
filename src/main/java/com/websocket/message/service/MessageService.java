package com.websocket.message.service;

import com.websocket.message.dto.MessageDto;
import com.websocket.message.entity.Message;
import com.websocket.message.mapper.MessageMapper;
import com.websocket.message.redis.RedisPublisher;
import com.websocket.message.repository.MessageRepository;
import com.websocket.messageroom.entity.MessageRoom;
import com.websocket.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final RedisPublisher redisPublisher;

    public MessageDto.Response createMessage(MessageDto.Post postDto, MessageRoom messageRoom, User sender, User receiver) {
        Message message = messageMapper.messagePostDtoToMessage(postDto);
        message.setProperties(messageRoom, sender, receiver);
        messageRoom.setMessageRoom(message);
        MessageDto.Response responseDto = messageMapper.messageToMessageResponseDto(messageRepository.save(message));
        publishMessageToRedis(messageRoom, responseDto);
        return responseDto;
    }

    private void publishMessageToRedis(MessageRoom messageRoom, MessageDto.Response messageDto) {
        String topic = "messageRoom" + messageRoom.getMessageRoomId();
        redisPublisher.publish(ChannelTopic.of(topic), messageDto);
    }
}
