package com.websocket.chat.message.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageStatus {
    ENTER(1),
    MESSAGE(2),
    LEAVE(3);

    private final int type;
}
