package com.websocket.messageroom.entity;

import com.websocket.message.entity.Message;
import com.websocket.common.entity.BaseTime;
import com.websocket.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageRoomId;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageRoomStatus messageRoomStatus;

    @Column(nullable = true)
    private String lastMessage;

    @Column(nullable = true)
    private Long lastSenderId;

    @Setter
    @JoinColumn(name = "sender_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User sender;

    @Setter
    @JoinColumn(name = "receiver_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User receiver;

    @OrderBy("messageId")
    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private Set<Message> messages = new LinkedHashSet<>();

    @Builder
    public MessageRoom(Long messageRoomId, User sender, User receiver) {
        this.messageRoomId = messageRoomId;
        this.sender = sender;
        this.receiver = receiver;
    }

    public void setProperties(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }

    public void setMessageRoom(Message message) {
        this.lastMessage = message.getContent();
        this.lastSenderId = message.getSender().getId();
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }
}
