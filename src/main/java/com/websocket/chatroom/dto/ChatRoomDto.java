package com.websocket.chatroom.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ChatRoomDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotNull
        @Positive
        private Long senderId;

        @NotNull
        @Positive
        private Long receiverId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {

        @NotNull
        @Positive
        private Long userId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long chatRoomId;
        private Long senderId;
        private Long receiverId;
        private Long leaverId;
    }
}
