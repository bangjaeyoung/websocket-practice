package com.websocket.chat.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class ChatDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotNull
        @Positive
        private Long chatRoomId;

        @NotNull
        @Positive
        private Long senderId;

        @NotNull
        @Positive
        private Long receiverId;

        @NotBlank
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long chatRoomId;
        private Long senderId;
        private Long receiverId;
        private String content;
        private LocalDateTime createdAt;
    }
}
