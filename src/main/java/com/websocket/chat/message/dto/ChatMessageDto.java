package com.websocket.chat.message.dto;

import com.websocket.chat.message.entity.MessageStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ChatMessageDto {

    @Getter
    @Builder
    public static class Request {
        private Long chatRoomId;

        @NotBlank
        private Long userId;

        @NotEmpty
        private MessageStatus status;

        @Setter
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long chatMessageId; // 채팅방 전체 채팅 조회 시 헷갈림 방지
        private Long userId;
        private String nickname;
        private String content;
        private String createdAt;
    }
}
