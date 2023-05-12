package com.websocket.chat.message.entity;

import com.websocket.chat.room.entity.ChatRoom;
import com.websocket.common.entity.BaseTime;
import com.websocket.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(String content, MessageStatus messageStatus, User sender, ChatRoom chatRoom) {
        this.content = content;
        this.messageStatus = messageStatus;
        this.sender = sender;
        this.chatRoom = chatRoom;
    }

    public void changeSender(User user) {
        this.sender = user;
    }

    public void changeChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
