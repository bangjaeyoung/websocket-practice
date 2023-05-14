package com.websocket.chatroom.entity;

import com.websocket.chat.entity.Chat;
import com.websocket.common.entity.BaseTime;
import com.websocket.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTime {
    //TODO BaseTime 불필요시 제거

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column
    private Long leaverId;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<Chat> chatList = new ArrayList<>();

    @Builder
    public ChatRoom(Long chatRoomId, User sender, User receiver) {
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getSenderId() {
        return sender.getId();
    }

    public Long getReceiverId() {
        return receiver.getId();
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
