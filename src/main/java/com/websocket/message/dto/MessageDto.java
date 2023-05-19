package com.websocket.message.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MessageDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotNull
        private Long messageRoomId;

        @NotNull
        private Long senderId;

        @NotNull
        private Long receiverId;

        @NotNull
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long messageId;
        private String content;
        private Long messageRoomId;
        private Long senderId;
        private String senderName;
        private Long receiverId;
        private String receiverName;
        private LocalDateTime createdAt;
    }
}
